package org.hexworks.mixite.core.internal.impl.layoutstrategy

import org.hexworks.mixite.core.GridLayoutStrategyTestUtil.defaultGridData
import org.hexworks.mixite.core.api.CubeCoordinate
import org.hexworks.mixite.core.api.HexagonOrientation.FLAT_TOP
import org.hexworks.mixite.core.api.HexagonOrientation.POINTY_TOP
import org.hexworks.mixite.core.api.HexagonalGridLayout.HEXAGONAL
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HexagonalGridLayoutStrategyTest {

    @Test
    fun shouldProperlyCreateHexagonsWithPointyOrientationWhenCreateHexagonsIsCalled() {
        val coordIter = HexagonalGridLayoutStrategy().fetchGridCoordinates(defaultGridData(HEXAGONAL, POINTY_TOP)).iterator()

        val coords = ArrayList<CubeCoordinate>()
        while (coordIter.hasNext()) {
            coords.add(coordIter.next())
        }

        assertTrue(coords.contains(CubeCoordinate(1, 0)))
        assertTrue(coords.contains(CubeCoordinate(2, 0)))
        assertTrue(coords.contains(CubeCoordinate(2, 1)))
        assertTrue(coords.contains(CubeCoordinate(1, 2)))
        assertTrue(coords.contains(CubeCoordinate(0, 2)))
        assertTrue(coords.contains(CubeCoordinate(0, 1)))

        assertTrue(!coords.contains(CubeCoordinate(0, 0)))
        assertTrue(!coords.contains(CubeCoordinate(1, -1)))
        assertTrue(!coords.contains(CubeCoordinate(2, -1)))
        assertTrue(!coords.contains(CubeCoordinate(3, -1)))
        assertTrue(!coords.contains(CubeCoordinate(3, 0)))
        assertTrue(!coords.contains(CubeCoordinate(3, 1)))
        assertTrue(!coords.contains(CubeCoordinate(2, 2)))
        assertTrue(!coords.contains(CubeCoordinate(1, 3)))
        assertTrue(!coords.contains(CubeCoordinate(0, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 2)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 1)))
    }

    @Test
    fun shouldProperlyCreateHexagonsWithPointyOrientationWhenCreateHexagonsIsCalledWithBiggerSize() {
        val coordIter = HexagonalGridLayoutStrategy().fetchGridCoordinates(defaultGridData(HEXAGONAL, POINTY_TOP, width = 5, height = 5)).iterator()

        val coords = ArrayList<CubeCoordinate>()
        while (coordIter.hasNext()) {
            coords.add(coordIter.next())
        }

        assertTrue(coords.contains(CubeCoordinate(1, 0)))
        assertTrue(coords.contains(CubeCoordinate(2, 0)))
        assertTrue(coords.contains(CubeCoordinate(3, 0)))
        assertTrue(coords.contains(CubeCoordinate(3, 1)))
        assertTrue(coords.contains(CubeCoordinate(3, 2)))
        assertTrue(coords.contains(CubeCoordinate(2, 3)))
        assertTrue(coords.contains(CubeCoordinate(1, 4)))
        assertTrue(coords.contains(CubeCoordinate(0, 4)))
        assertTrue(coords.contains(CubeCoordinate(-1, 4)))
        assertTrue(coords.contains(CubeCoordinate(-1, 3)))
        assertTrue(coords.contains(CubeCoordinate(-1, 2)))
        assertTrue(coords.contains(CubeCoordinate(0, 1)))

        assertTrue(!coords.contains(CubeCoordinate(0, 0)))
        assertTrue(!coords.contains(CubeCoordinate(1, -1)))
        assertTrue(!coords.contains(CubeCoordinate(2, -1)))
        assertTrue(!coords.contains(CubeCoordinate(3, -1)))
        assertTrue(!coords.contains(CubeCoordinate(4, -1)))
        assertTrue(!coords.contains(CubeCoordinate(4, 0)))
        assertTrue(!coords.contains(CubeCoordinate(4, 1)))
        assertTrue(!coords.contains(CubeCoordinate(4, 2)))
        assertTrue(!coords.contains(CubeCoordinate(3, 3)))
        assertTrue(!coords.contains(CubeCoordinate(2, 4)))
        assertTrue(!coords.contains(CubeCoordinate(1, 5)))
        assertTrue(!coords.contains(CubeCoordinate(0, 5)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 5)))
        assertTrue(!coords.contains(CubeCoordinate(-2, 5)))
        assertTrue(!coords.contains(CubeCoordinate(-2, 4)))
        assertTrue(!coords.contains(CubeCoordinate(-2, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-2, 2)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 1)))
    }

    @Test
    fun shouldProperlyCreateHexagonsWithFlatOrientationWhenCreateHexagonsIsCalled() {
        val coordIter = HexagonalGridLayoutStrategy().fetchGridCoordinates(defaultGridData(HEXAGONAL, FLAT_TOP)).iterator()

        val coords = ArrayList<CubeCoordinate>()
        while (coordIter.hasNext()) {
            coords.add(coordIter.next())
        }


        assertTrue(coords.contains(CubeCoordinate(1, 0)))
        assertTrue(coords.contains(CubeCoordinate(2, 0)))
        assertTrue(coords.contains(CubeCoordinate(2, 1)))
        assertTrue(coords.contains(CubeCoordinate(1, 2)))
        assertTrue(coords.contains(CubeCoordinate(0, 2)))
        assertTrue(coords.contains(CubeCoordinate(0, 1)))

        assertTrue(!coords.contains(CubeCoordinate(0, 0)))
        assertTrue(!coords.contains(CubeCoordinate(0, -1)))
        assertTrue(!coords.contains(CubeCoordinate(2, -1)))
        assertTrue(!coords.contains(CubeCoordinate(3, -1)))
        assertTrue(!coords.contains(CubeCoordinate(3, 0)))
        assertTrue(!coords.contains(CubeCoordinate(3, 1)))
        assertTrue(!coords.contains(CubeCoordinate(2, 2)))
        assertTrue(!coords.contains(CubeCoordinate(1, 3)))
        assertTrue(!coords.contains(CubeCoordinate(0, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 2)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 1)))
    }

    @Test
    fun shouldProperlyCreateHexagonsWithFlatOrientationWhenCreateHexagonsIsCalledWithBiggerSize() {
        val coordIter = HexagonalGridLayoutStrategy().fetchGridCoordinates(defaultGridData(HEXAGONAL, FLAT_TOP, width = 5, height = 5)).iterator()

        val coords = ArrayList<CubeCoordinate>()
        while (coordIter.hasNext()) {
            coords.add(coordIter.next())
        }


        assertTrue(coords.contains(CubeCoordinate(2, -1)))
        assertTrue(coords.contains(CubeCoordinate(3, -1)))
        assertTrue(coords.contains(CubeCoordinate(4, -1)))
        assertTrue(coords.contains(CubeCoordinate(4, 0)))
        assertTrue(coords.contains(CubeCoordinate(4, 1)))
        assertTrue(coords.contains(CubeCoordinate(3, 2)))
        assertTrue(coords.contains(CubeCoordinate(2, 3)))
        assertTrue(coords.contains(CubeCoordinate(1, 3)))
        assertTrue(coords.contains(CubeCoordinate(0, 3)))
        assertTrue(coords.contains(CubeCoordinate(0, 2)))
        assertTrue(coords.contains(CubeCoordinate(0, 1)))
        assertTrue(coords.contains(CubeCoordinate(1, 0)))

        assertTrue(!coords.contains(CubeCoordinate(0, 0)))
        assertTrue(!coords.contains(CubeCoordinate(1, -1)))
        assertTrue(!coords.contains(CubeCoordinate(2, -2)))
        assertTrue(!coords.contains(CubeCoordinate(3, -2)))
        assertTrue(!coords.contains(CubeCoordinate(4, -2)))
        assertTrue(!coords.contains(CubeCoordinate(5, -2)))
        assertTrue(!coords.contains(CubeCoordinate(5, -1)))
        assertTrue(!coords.contains(CubeCoordinate(5, 0)))
        assertTrue(!coords.contains(CubeCoordinate(5, 1)))
        assertTrue(!coords.contains(CubeCoordinate(4, 2)))
        assertTrue(!coords.contains(CubeCoordinate(3, 3)))
        assertTrue(!coords.contains(CubeCoordinate(2, 4)))
        assertTrue(!coords.contains(CubeCoordinate(1, 4)))
        assertTrue(!coords.contains(CubeCoordinate(0, 4)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 4)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 2)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 1)))
    }

    @Test
    fun testCheckParameters0() {
        val result = HEXAGONAL.checkParameters(1, 1) // super: true, derived: true
        assertTrue(result)
    }

    @Test
    fun testCheckParameters1() {
        val result = HEXAGONAL.checkParameters(1, 2) // super: true, derived: false
        assertFalse(result)
    }

    @Test
    fun testCheckParameters2() {
        val result = HEXAGONAL.checkParameters(2, 2) // super: true, derived: false
        assertFalse(result)
    }

    @Test
    fun testCheckParameters3() {
        val result = HEXAGONAL.checkParameters(0, 0) // super: false, derived: false;
        assertFalse(result)
    }

    @Test
    fun testCheckParameters4() {
        val result = HEXAGONAL.checkParameters(-1, -1) // super: false, derived: true;
        assertFalse(result)
    }

}
