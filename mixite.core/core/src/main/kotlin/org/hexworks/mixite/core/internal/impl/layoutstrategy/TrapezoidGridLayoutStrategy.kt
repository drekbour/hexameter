package org.hexworks.mixite.core.internal.impl.layoutstrategy

import org.hexworks.mixite.core.api.CubeCoordinate
import org.hexworks.mixite.core.internal.GridData
import org.hexworks.mixite.core.internal.impl.layoutstrategy.GridLayoutStrategy.Companion.checkCommonCase

object TrapezoidGridLayoutStrategy : GridLayoutStrategy {

    private fun count(height: Int, width: Int) = width * height

    override fun fetchGridCoordinates(gridData: GridData): Sequence<CubeCoordinate> {
        return sequence{
            with(gridData) {
                for (gridZ in 0 until gridHeight) {
                    for (gridX in 0 until gridWidth) {
                        yield(CubeCoordinate(gridX, gridZ))
                    }
                }
            }
        }
    }

    override fun checkParameters(gridHeight: Int, gridWidth: Int): Boolean {
        return checkCommonCase(gridHeight, gridWidth)
    }
}
