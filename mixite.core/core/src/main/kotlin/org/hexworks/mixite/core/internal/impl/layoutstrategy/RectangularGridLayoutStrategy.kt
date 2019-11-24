package org.hexworks.mixite.core.internal.impl.layoutstrategy

import org.hexworks.mixite.core.api.CoordinateConverter
import org.hexworks.mixite.core.api.CubeCoordinate
import org.hexworks.mixite.core.api.HexagonalGridBuilder
import org.hexworks.mixite.core.api.contract.SatelliteData
import org.hexworks.mixite.core.internal.GridData

class RectangularGridLayoutStrategy : GridLayoutStrategy() {

    override fun fetchGridCoordinates(gridData: GridData): Iterable<CubeCoordinate> {
        with(gridData) {
            val coords = ArrayList<CubeCoordinate>(gridHeight * gridWidth)
            for (y in 0 until gridHeight) {
                for (x in 0 until gridWidth) {
                    val gridX = CoordinateConverter.convertOffsetCoordinatesToCubeX(x, y, orientation)
                    val gridZ = CoordinateConverter.convertOffsetCoordinatesToCubeZ(x, y, orientation)
                    coords.add(CubeCoordinate.fromCoordinates(gridX, gridZ))
                }
            }
            return coords
        }
    }

    override fun checkParameters(gridHeight: Int, gridWidth: Int): Boolean {
        return checkCommonCase(gridHeight, gridWidth)
    }
}
