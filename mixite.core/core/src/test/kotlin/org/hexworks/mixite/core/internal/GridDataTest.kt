package org.hexworks.mixite.core.internal

import org.hexworks.mixite.core.api.HexagonOrientation.FLAT_TOP
import org.hexworks.mixite.core.api.HexagonOrientation.POINTY_TOP
import org.hexworks.mixite.core.api.HexagonalGridLayout.RECTANGULAR
import kotlin.math.sqrt
import kotlin.test.Test
import kotlin.test.assertEquals

class GridDataTest {

    @Test
    fun shouldProperlyReturnRadiusWhenGetRadiusIsCalled() {
        val target = createWithFlat()
        assertEquals(RADIUS, target.radius)
    }

    @Test
    fun shouldProperlyCalculateWidthWithPointyHexagonsWhenGetWidthIsCalled() {
        val target = createWithPointy()
        assertEquals(sqrt(3.0) * RADIUS, target.hexagonWidth)
    }

    @Test
    fun shouldProperlyCalculateWidthWithFlatHexagonsWhenGetWidthIsCalled() {
        val target = createWithFlat()
        assertEquals(RADIUS * 3 / 2, target.hexagonWidth)
    }

    @Test
    fun shouldProperlyCalculateHeightWithPointyHexagonsWhenGetHeightIsCalled() {
        val target = createWithPointy()
        assertEquals(RADIUS * 3 / 2, target.hexagonHeight)
    }

    @Test
    fun shouldProperlyCalculateHeightWithFlatHexagonsWhenGetHeightIsCalled() {
        val target = createWithFlat()
        assertEquals(sqrt(3.0) * RADIUS, target.hexagonHeight)
    }

    @Test
    fun shouldReturnProperOrientationWhenGetOrientationIsCalled() {
        val target = createWithFlat()
        assertEquals(FLAT_TOP, target.orientation)
    }

    private fun createWithPointy(): GridData {
        return GridData(POINTY_TOP, GRID_LAYOUT, RADIUS, GRID_WIDTH, GRID_HEIGHT)
    }

    private fun createWithFlat(): GridData {
        return GridData(FLAT_TOP, GRID_LAYOUT, RADIUS, GRID_WIDTH, GRID_HEIGHT)
    }

    companion object {
        private const val RADIUS = 30.0
        private const val GRID_WIDTH = 30
        private const val GRID_HEIGHT = 30
        private val GRID_LAYOUT = RECTANGULAR
    }
}
