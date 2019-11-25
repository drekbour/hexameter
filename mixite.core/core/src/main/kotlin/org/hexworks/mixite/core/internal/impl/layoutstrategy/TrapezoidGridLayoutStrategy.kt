package org.hexworks.mixite.core.internal.impl.layoutstrategy

import org.hexworks.mixite.core.api.CubeCoordinate
import org.hexworks.mixite.core.api.HexagonalGridBuilder
import org.hexworks.mixite.core.api.contract.SatelliteData
import org.hexworks.mixite.core.internal.GridData
import org.hexworks.mixite.core.internal.impl.layoutstrategy.GridLayoutStrategy.Companion.checkCommonCase

object TrapezoidGridLayoutStrategy : GridLayoutStrategy {

    private fun count(height: Int, width: Int) = width * height

    override fun fetchGridCoordinates(gridData: GridData): Iterable<CubeCoordinate> {
        with(gridData) {
            val coords = ArrayList<CubeCoordinate>(count(gridHeight, gridWidth))
            for (gridZ in 0 until gridHeight) {
                for (gridX in 0 until gridWidth) {
                    coords.add(CubeCoordinate(gridX, gridZ))
                }
            }
            return coords
        }
    }

    override fun checkParameters(gridHeight: Int, gridWidth: Int): Boolean {
        return checkCommonCase(gridHeight, gridWidth)
    }
}
