package org.hexworks.mixite.core.internal.impl.layoutstrategy

import org.hexworks.mixite.core.api.CubeCoordinate
import org.hexworks.mixite.core.api.HexagonalGridBuilder
import org.hexworks.mixite.core.api.contract.SatelliteData
import org.hexworks.mixite.core.internal.GridData

class TrapezoidGridLayoutStrategy : GridLayoutStrategy() {

    override fun fetchGridCoordinates(gridData: GridData): Iterable<CubeCoordinate> {
        with ( gridData) {
            val coords = ArrayList<CubeCoordinate>(gridHeight * gridWidth)
            for (gridZ in 0 until gridHeight) {
                for (gridX in 0 until gridWidth) {
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
