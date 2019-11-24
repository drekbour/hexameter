package org.hexworks.mixite.core.internal.impl.layoutstrategy

import org.hexworks.mixite.core.api.CubeCoordinate
import org.hexworks.mixite.core.api.HexagonalGridBuilder
import org.hexworks.mixite.core.api.contract.SatelliteData
import org.hexworks.mixite.core.internal.GridData

class TriangularGridLayoutStrategy : GridLayoutStrategy() {

    override fun fetchGridCoordinates(gridData: GridData): Iterable<CubeCoordinate> {
        with (gridData) {
            val gridSize = gridHeight
            val coords = ArrayList<CubeCoordinate>(gridSize * (gridSize + 1) / 2)
            for (gridZ in 0 until gridSize) {
                val endX = gridSize - gridZ
                for (gridX in 0 until endX) {
                    coords.add(CubeCoordinate.fromCoordinates(gridX, gridZ))
                }
            }
            return coords
        }
    }

    override fun checkParameters(gridHeight: Int, gridWidth: Int): Boolean {
        val superResult = checkCommonCase(gridHeight, gridWidth)
        val result = gridHeight == gridWidth
        return superResult && result
    }
}
