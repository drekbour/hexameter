package org.hexworks.mixite.core.internal.impl.layoutstrategy

import org.hexworks.mixite.core.GridLayoutStrategyTestUtil.defaultGridData
import org.hexworks.mixite.core.api.CubeCoordinate
import org.hexworks.mixite.core.api.HexagonOrientation.FLAT_TOP
import org.hexworks.mixite.core.api.HexagonOrientation.POINTY_TOP
import org.hexworks.mixite.core.api.HexagonalGridLayout.TRIANGULAR
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TriangularGridLayoutStrategyTest {

    @Test
    fun shouldProperlyCreateHexagonsWithPointyOrientationWhenCreateHexagonsIsCalled() {
        testCoordinates(TriangularGridLayoutStrategy().fetchGridCoordinates(defaultGridData(TRIANGULAR, POINTY_TOP)).iterator())
    }

    @Test
    fun shouldProperlyCreateHexagonsWithFlatOrientationWhenCreateHexagonsIsCalled() {
        testCoordinates(TriangularGridLayoutStrategy().fetchGridCoordinates(defaultGridData(TRIANGULAR, FLAT_TOP)).iterator())
    }

    @Test
    fun shouldReturnTrueWhenCheckParametersWithOneAndOne() {
        val result = TRIANGULAR.checkParameters(1, 1) // super: true, derived: true
        assertTrue(result)
    }

    @Test
    fun shouldReturnTrueWhenCheckParametersWithOneAndTwo() {
        val result = TRIANGULAR.checkParameters(1, 2) // super: true, derived: false
        assertFalse(result)
    }

    @Test
    fun shouldReturnTrueWhenCheckParametersWithZeroAndZero() {
        val result = TRIANGULAR.checkParameters(0, 0) // super: false, derived: false;
        assertFalse(result)
    }

    @Test
    fun shouldReturnTrueWhenCheckParametersWithMinusOneAndMinusOne() {
        val result = TRIANGULAR.checkParameters(-1, -1) // super: false, derived: true;
        assertFalse(result)
    }

    private fun testCoordinates(coordIter: Iterator<CubeCoordinate>) {
        val coords = ArrayList<CubeCoordinate>()
        while (coordIter.hasNext()) {
            coords.add(coordIter.next())
        }
        assertTrue(coords.contains(CubeCoordinate(0, 0)))
        assertTrue(coords.contains(CubeCoordinate(1, 0)))
        assertTrue(coords.contains(CubeCoordinate(2, 0)))
        assertTrue(coords.contains(CubeCoordinate(0, 1)))
        assertTrue(coords.contains(CubeCoordinate(1, 1)))
        assertTrue(coords.contains(CubeCoordinate(0, 2)))

        assertTrue(!coords.contains(CubeCoordinate(-1, 0)))
        assertTrue(!coords.contains(CubeCoordinate(0, -1)))
        assertTrue(!coords.contains(CubeCoordinate(1, -1)))
        assertTrue(!coords.contains(CubeCoordinate(2, -1)))
        assertTrue(!coords.contains(CubeCoordinate(3, -1)))
        assertTrue(!coords.contains(CubeCoordinate(3, 0)))
        assertTrue(!coords.contains(CubeCoordinate(2, 1)))
        assertTrue(!coords.contains(CubeCoordinate(1, 2)))
        assertTrue(!coords.contains(CubeCoordinate(0, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 2)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 1)))
    }
}

