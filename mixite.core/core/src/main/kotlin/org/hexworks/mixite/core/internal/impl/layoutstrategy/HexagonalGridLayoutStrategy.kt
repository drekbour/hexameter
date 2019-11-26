package org.hexworks.mixite.core.internal.impl.layoutstrategy

import org.hexworks.mixite.core.api.CubeCoordinate
import org.hexworks.mixite.core.api.HexagonOrientation.*
import org.hexworks.mixite.core.internal.GridData
import org.hexworks.mixite.core.internal.impl.layoutstrategy.GridLayoutStrategy.Companion.checkCommonCase
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.round

object HexagonalGridLayoutStrategy : GridLayoutStrategy {

    private fun count(height: Int, width: Int) = 1 + (height * width * 6 - 6) / 8

    override fun fetchGridCoordinates(gridData: GridData): Sequence<CubeCoordinate> {
        return sequence {
            with(gridData) {
                val gridSize = gridHeight
                var startX = if (orientation === FLAT_TOP) floor(gridSize / 2.0).toInt() else round(gridSize / 4.0).toInt()
                val hexRadius = gridSize shr 1
                val minX = startX - hexRadius
                var y = 0
                while (y < gridSize) {
                    val distanceFromMid = abs(hexRadius - y)
                    for (x in max(startX, minX)..max(startX, minX) + hexRadius + hexRadius - distanceFromMid) {
                        val gridZ = if (orientation === FLAT_TOP) y - floor(gridSize / 4.0).toInt() else y
                        yield(CubeCoordinate(x, gridZ))
                    }
                    startX--
                    y++
                }
            }
        }
    }

    override fun checkParameters(gridHeight: Int, gridWidth: Int): Boolean {
        return checkCommonCase(gridHeight, gridWidth) && (gridHeight == gridWidth) && abs(gridHeight % 2) == 1
    }

}
