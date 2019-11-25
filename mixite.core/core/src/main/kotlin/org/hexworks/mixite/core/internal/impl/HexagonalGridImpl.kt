package org.hexworks.mixite.core.internal.impl

import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.mixite.core.api.*
import org.hexworks.mixite.core.api.contract.HexagonDataStorage
import org.hexworks.mixite.core.api.contract.SatelliteData
import org.hexworks.mixite.core.internal.GridData
import org.hexworks.mixite.core.internal.impl.layoutstrategy.GridLayoutStrategy
import kotlin.math.abs

class HexagonalGridImpl<T : SatelliteData>(
        override val gridData: GridData,
        private val hexagonDataStorage: HexagonDataStorage<T>,
        gridLayoutStrategy: GridLayoutStrategy) : HexagonalGrid<T> {

    override val hexagons: Iterable<Hexagon<T>>
        get() = hexagonsFor(hexagonDataStorage.coordinates)

    init {
        for (cubeCoordinate in gridLayoutStrategy.fetchGridCoordinates(gridData)) {
            hexagonDataStorage.addCoordinate(cubeCoordinate)
        }
    }

    override fun getHexagonsByCubeRange(from: CubeCoordinate, to: CubeCoordinate): Iterable<Hexagon<T>> {
        val coordinates = ArrayList<CubeCoordinate>(abs(from.gridZ-to.gridZ) + abs(from.gridX-to.gridX))

        for (gridZ in from.gridZ..to.gridZ) {
            for (gridX in from.gridX..to.gridX) {
                val coord = CubeCoordinate(gridX, gridZ)
                if (containsCubeCoordinate(coord)) {
                    coordinates.add(coord)
                }
            }
        }

        return hexagonsFor(coordinates)
    }

    override fun getHexagonsByOffsetRange(gridXFrom: Int, gridXTo: Int, gridYFrom: Int, gridYTo: Int): Iterable<Hexagon<T>> {
        val coordinates = ArrayList<CubeCoordinate>()

        for (gridX in gridXFrom..gridXTo) {
            for (gridY in gridYFrom..gridYTo) {
                val coord = CoordinateConverter.offsetToCubeCoordinate(gridX, gridY, gridData.orientation)
                if (containsCubeCoordinate(coord)) {
                    coordinates.add(coord)
                }
            }
        }
        return hexagonsFor(coordinates)
    }

    override fun containsCubeCoordinate(coordinate: CubeCoordinate): Boolean {
        return this.hexagonDataStorage.containsCoordinate(coordinate)
    }

    private fun _getByCubeCoordinate(coordinate: CubeCoordinate) =
            if (containsCubeCoordinate(coordinate)) hexagon(coordinate) else null

    override fun getByCubeCoordinate(coordinate: CubeCoordinate): Maybe<Hexagon<T>> =
            Maybe.ofNullable(_getByCubeCoordinate(coordinate))

    override fun getByPixelCoordinate(coordinateX: Double, coordinateY: Double): Maybe<Hexagon<T>> {
        val offsetX = (coordinateX / gridData.hexagonWidth).toInt()
        val offsetY = (coordinateY / gridData.hexagonHeight).toInt()
        // it is possible that the estimated coordinates are off-grid so we create a virtual hexagon
        val estimatedCoordinate = CoordinateConverter.offsetToCubeCoordinate(offsetX, offsetY, gridData.orientation)
        val centerHex = hexagon(estimatedCoordinate)
        val nearestHex = nearestHexagonToPoint(centerHex, Point(coordinateX, coordinateY))

        return if (nearestHex === centerHex) {
            getByCubeCoordinate(estimatedCoordinate) // centerHex may have been off-grid so look it up again
        } else {
            Maybe.of(nearestHex) // Any other result must be a (real) neighbour
        }
    }

    private fun _getNeighborByIndex(hexagon: Hexagon<T>, index: Int) =
            CubeCoordinate(
                    hexagon.gridX + NEIGHBORS[index][NEIGHBOR_X_INDEX],
                    hexagon.gridZ + NEIGHBORS[index][NEIGHBOR_Z_INDEX]
            )

    override fun getNeighborByIndex(hexagon: Hexagon<T>, index: Int) =
            getByCubeCoordinate(_getNeighborByIndex(hexagon, index))

    override fun getNeighborsOf(hexagon: Hexagon<T>): Collection<Hexagon<T>> {
         // TODO NEIGHBORS.indices.map { i -> _getNeighborByIndex(hexagon, i) }.mapNotNull(this::_getByCubeCoordinate)
        val neighbors = HashSet<Hexagon<T>>()
        for (i in NEIGHBORS.indices) {
            val retHex = getNeighborByIndex(hexagon, i)
            if (retHex.isPresent) {
                neighbors.add(retHex.get())
            }
        }
        return neighbors
    }

    /*
     * Returns either the original center hex or the nearest (real) hex around it
     */
    private fun nearestHexagonToPoint(centerHex: Hexagon<T>, point: Point): Hexagon<T> {
        var nearest = centerHex
        var nearestDistance = Double.MAX_VALUE
        var current: Hexagon<T>? = nearest // Start with center then check six neighbours

        var i = 0;
        while(true) {
            current?.let {
                val currentDistance = point.distanceFrom(it.center)
                when  {
                    currentDistance < gridData.innerRadius -> return it // Shortcut if well inside bounds of current hex
                    currentDistance < nearestDistance -> {
                        // This covers points right in the corner between three hex's (not within innerRadius of any of them)
                        // TODO In theory, we can shortcut if we have refined twice!
                        nearest = it
                        nearestDistance = currentDistance
                    }
                }
            }

            if (i == 6) {
                return nearest // No direct match, pick the nearest one
            }
            current = _getByCubeCoordinate(_getNeighborByIndex(centerHex, i++))
        }
    }

    private fun hexagon(coordinate: CubeCoordinate): HexagonImpl<T> =
            HexagonImpl(gridData, coordinate, hexagonDataStorage)

    private fun hexagonsFor(coordinates: Iterable<CubeCoordinate>): Iterable<Hexagon<T>> {
        // TODO return coordinates.map { c -> hexagon(c) } is _Really Slow_
        val iter = coordinates.iterator()

        return object : Iterable<Hexagon<T>> {
            override fun iterator(): Iterator<Hexagon<T>> {
                return object : Iterator<Hexagon<T>> {
                    override fun hasNext(): Boolean {
                        return iter.hasNext()
                    }

                    override fun next(): Hexagon<T> {
                        return hexagon(iter.next())
                    }
                }
            }
        }
    }

    companion object {
        private val NEIGHBORS = arrayOf(intArrayOf(+1, 0), intArrayOf(+1, -1), intArrayOf(0, -1), intArrayOf(-1, 0), intArrayOf(-1, +1), intArrayOf(0, +1))
        private const val NEIGHBOR_X_INDEX = 0
        private const val NEIGHBOR_Z_INDEX = 1
    }
}
