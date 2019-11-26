package org.hexworks.mixite.core.internal.impl

import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.mixite.core.api.*
import org.hexworks.mixite.core.api.contract.HexagonDataStorage
import org.hexworks.mixite.core.api.contract.SatelliteData
import org.hexworks.mixite.core.internal.GridData
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Default implementation of the [Hexagon] interface.
 */
class HexagonImpl<T : SatelliteData> internal constructor(
        private val sharedData: GridData,
        override val cubeCoordinate: CubeCoordinate,
        private val hexagonDataStorage: HexagonDataStorage<T>) : Hexagon<T> {

    // Cube identity
    override val id = cubeCoordinate.toAxialKey()
    override val gridX = cubeCoordinate.gridX
    override val gridY = cubeCoordinate.gridY
    override val gridZ = cubeCoordinate.gridZ

    // 2D identity
    override val vertices: List<Double>
    override val points: List<Point>
    override val externalBoundingBox: Rectangle
    override val internalBoundingBox: Rectangle
    override val center: Point = calculateCenter()
    override val centerX = center.coordinateX
    override val centerY = center.coordinateY

    // User identity
    override val satelliteData: Maybe<T>
      get() = hexagonDataStorage.getSatelliteDataBy(cubeCoordinate)

    init {
        this.points = calculatePoints()
        val x1 = points[3].coordinateX
        val y1 = points[2].coordinateY
        val x2 = points[0].coordinateX
        val y2 = points[5].coordinateY

        externalBoundingBox = Rectangle(x1, y1, x2 - x1, y2 - y1)
        internalBoundingBox = Rectangle((center.coordinateX - 1.25 * sharedData.radius / 2),
                (center.coordinateY - 1.25 * sharedData.radius / 2),
                (1.25f * sharedData.radius),
                (1.25f * sharedData.radius))

        this.vertices = ArrayList(12)
        for (point in points) {
            vertices += point.coordinateX
            vertices += point.coordinateY
        }
    }

    private fun calculateCenter(): Point {
        return if (HexagonOrientation.FLAT_TOP === sharedData.orientation) {
            Point(
                    cubeCoordinate.gridX * sharedData.hexagonWidth + sharedData.radius,
                    cubeCoordinate.gridZ * sharedData.hexagonHeight + cubeCoordinate.gridX * sharedData.hexagonHeight / 2 + sharedData.hexagonHeight / 2
            )
        } else {
            Point(
                    cubeCoordinate.gridX * sharedData.hexagonWidth + cubeCoordinate.gridZ * sharedData.hexagonWidth / 2 + sharedData.hexagonWidth / 2,
                    cubeCoordinate.gridZ * sharedData.hexagonHeight + sharedData.radius
            )
        }
    }

    private fun calculatePoints(): List<Point> {
        val points = ArrayList<Point>(6)
        for (i in 0..5) {
            val angle = 2 * PI / 6 * (i + sharedData.orientation.coordinateOffset)
            val x = center.coordinateX + sharedData.radius * cos(angle)
            val y = center.coordinateY + sharedData.radius * sin(angle)
            points += Point(x, y)
        }
        return points
    }

    override fun setSatelliteData(data: T) {
        hexagonDataStorage.addCoordinate(cubeCoordinate, data)
    }

    override fun clearSatelliteData() {
        hexagonDataStorage.clearDataFor(cubeCoordinate)
    }

    override fun equals(other: Any?) = other is HexagonImpl<*> && cubeCoordinate == other.cubeCoordinate

    override fun hashCode() = cubeCoordinate.hashCode()

}
