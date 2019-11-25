package org.hexworks.mixite.core.internal.impl.layoutstrategy

import org.hexworks.mixite.core.GridLayoutStrategyTestUtil.defaultGridData
import org.hexworks.mixite.core.api.CubeCoordinate
import org.hexworks.mixite.core.api.HexagonOrientation.FLAT_TOP
import org.hexworks.mixite.core.api.HexagonOrientation.POINTY_TOP
import org.hexworks.mixite.core.api.HexagonalGridLayout.RECTANGULAR
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RectangularGridLayoutStrategyTest {

    @Test
    fun shouldProperlyCreateHexagonsWithPointyOrientationWhenCreateHexagonsIsCalled() {

        val coordIter = RectangularGridLayoutStrategy.fetchGridCoordinates(defaultGridData(RECTANGULAR, POINTY_TOP)).iterator()
        val coords = ArrayList<CubeCoordinate>()
        while (coordIter.hasNext()) {
            coords.add(coordIter.next())
        }

        assertTrue(coords.contains(CubeCoordinate(0, 0)))
        assertTrue(coords.contains(CubeCoordinate(1, 0)))
        assertTrue(coords.contains(CubeCoordinate(2, 0)))
        assertTrue(coords.contains(CubeCoordinate(0, 1)))
        assertTrue(coords.contains(CubeCoordinate(1, 1)))
        assertTrue(coords.contains(CubeCoordinate(2, 1)))
        assertTrue(coords.contains(CubeCoordinate(-1, 2)))
        assertTrue(coords.contains(CubeCoordinate(0, 2)))
        assertTrue(coords.contains(CubeCoordinate(1, 2)))

        assertTrue(!coords.contains(CubeCoordinate(-1, 0)))
        assertTrue(!coords.contains(CubeCoordinate(0, -1)))
        assertTrue(!coords.contains(CubeCoordinate(1, -1)))
        assertTrue(!coords.contains(CubeCoordinate(2, -1)))
        assertTrue(!coords.contains(CubeCoordinate(3, -1)))
        assertTrue(!coords.contains(CubeCoordinate(3, 0)))
        assertTrue(!coords.contains(CubeCoordinate(3, 1)))
        assertTrue(!coords.contains(CubeCoordinate(2, 2)))
        assertTrue(!coords.contains(CubeCoordinate(1, 3)))
        assertTrue(!coords.contains(CubeCoordinate(0, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-2, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-2, 2)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 1)))
    }

    @Test
    fun shouldProperlyCreateHexagonsWithFlatOrientationWhenCreateHexagonsIsCalled() {
        val coordIter = RectangularGridLayoutStrategy.fetchGridCoordinates(defaultGridData(RECTANGULAR, FLAT_TOP)).iterator()

        val coords = ArrayList<CubeCoordinate>()
        while (coordIter.hasNext()) {
            coords.add(coordIter.next())
        }

        assertTrue(coords.contains(CubeCoordinate(0, 0)))
        assertTrue(coords.contains(CubeCoordinate(1, 0)))
        assertTrue(coords.contains(CubeCoordinate(2, -1)))
        assertTrue(coords.contains(CubeCoordinate(0, 1)))
        assertTrue(coords.contains(CubeCoordinate(1, 1)))
        assertTrue(coords.contains(CubeCoordinate(2, 0)))
        assertTrue(coords.contains(CubeCoordinate(2, 1)))
        assertTrue(coords.contains(CubeCoordinate(0, 2)))
        assertTrue(coords.contains(CubeCoordinate(1, 2)))

        assertTrue(!coords.contains(CubeCoordinate(-1, 0)))
        assertTrue(!coords.contains(CubeCoordinate(0, -1)))
        assertTrue(!coords.contains(CubeCoordinate(1, -1)))
        assertTrue(!coords.contains(CubeCoordinate(2, -2)))
        assertTrue(!coords.contains(CubeCoordinate(3, -1)))
        assertTrue(!coords.contains(CubeCoordinate(3, 0)))
        assertTrue(!coords.contains(CubeCoordinate(3, 1)))
        assertTrue(!coords.contains(CubeCoordinate(2, 2)))
        assertTrue(!coords.contains(CubeCoordinate(1, 3)))
        assertTrue(!coords.contains(CubeCoordinate(0, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-2, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-2, 2)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 1)))
    }

    @Test
    fun testCheckParameters0() {
        val result = RECTANGULAR.checkParameters(1, 1) // super: true, derived: true
        assertTrue(result)
    }

    @Test
    fun testCheckParameters1() {
        val result = RECTANGULAR.checkParameters(0, 0) // super: false, derived: false;
        assertFalse(result)
    }

    @Test
    fun testCheckParameters2() {
        val result = RECTANGULAR.checkParameters(-1, -1) // super: false, derived: true;
        assertFalse(result)
    }

    @Test
    fun testCheckParameters3() {
        val result = RECTANGULAR.checkParameters(1, 2) // super: true, derived: true
        assertTrue(result)
    }

}
