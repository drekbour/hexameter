package org.hexworks.mixite.core

import org.hexworks.mixite.core.api.HexagonOrientation
import org.hexworks.mixite.core.api.HexagonalGridLayout
import org.hexworks.mixite.core.internal.GridData

object GridLayoutStrategyTestUtil {

    private const val RADIUS = 30.0
    private const val GRID_WIDTH = 3
    private const val GRID_HEIGHT = 3
    private val ORIENTATION = HexagonOrientation.POINTY_TOP

    fun defaultGridData(
            layout: HexagonalGridLayout,
            orientation: HexagonOrientation = ORIENTATION,
            width: Int = GRID_WIDTH,
            height:Int = GRID_HEIGHT
    ) =  GridData(orientation, layout, RADIUS, width, height)
}
