package org.hexworks.mixite.core.internal.impl.layoutstrategy

import org.hexworks.mixite.core.api.CoordinateConverter
import org.hexworks.mixite.core.api.CubeCoordinate
import org.hexworks.mixite.core.internal.GridData
import org.hexworks.mixite.core.internal.impl.layoutstrategy.GridLayoutStrategy.Companion.checkCommonCase

object RectangularGridLayoutStrategy : GridLayoutStrategy {

    private fun count(height: Int, width: Int) = width * height

    override fun fetchGridCoordinates(gridData: GridData): Sequence<CubeCoordinate> {
        return sequence {
            with(gridData) {
                for (y in 0 until gridHeight) {
                    for (x in 0 until gridWidth) {
                        yield(CoordinateConverter.offsetToCubeCoordinate(x, y, orientation))
                    }
                }
            }
        }
    }

    override fun checkParameters(gridHeight: Int, gridWidth: Int): Boolean {
        return checkCommonCase(gridHeight, gridWidth)
    }
}
