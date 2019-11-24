package org.hexworks.mixite.core.internal.impl.layoutstrategy

import org.hexworks.mixite.core.GridLayoutStrategyTestUtil.defaultGridData
import org.hexworks.mixite.core.api.CubeCoordinate
import org.hexworks.mixite.core.api.HexagonOrientation.FLAT_TOP
import org.hexworks.mixite.core.api.HexagonOrientation.POINTY_TOP
import org.hexworks.mixite.core.api.HexagonalGridLayout.TRAPEZOID
import kotlin.test.Test
import kotlin.test.assertTrue

class TrapezoidGridLayoutStrategyTest {

    @Test
    fun shouldProperlyCreateHexagonsWithPointyOrientationWhenCreateHexagonsIsCalled() {
        testCoordinates(TrapezoidGridLayoutStrategy().fetchGridCoordinates(defaultGridData(TRAPEZOID, POINTY_TOP)).iterator())
    }

    @Test
    fun shouldProperlyCreateHexagonsWithFlatOrientationWhenCreateHexagonsIsCalled() {
        testCoordinates(TrapezoidGridLayoutStrategy().fetchGridCoordinates(defaultGridData(TRAPEZOID, FLAT_TOP)).iterator())
    }

    private fun testCoordinates(coordIter: Iterator<CubeCoordinate>) {
        val coords = ArrayList<CubeCoordinate>()
        while (coordIter.hasNext()) {
            coords.add(coordIter.next())
        }

        assertTrue(coords.contains(CubeCoordinate(0, 0)))
        assertTrue(coords.contains(CubeCoordinate(1, 0)))
        assertTrue(coords.contains(CubeCoordinate(2, 0)))
        assertTrue(coords.contains(CubeCoordinate(2, 1)))
        assertTrue(coords.contains(CubeCoordinate(2, 2)))
        assertTrue(coords.contains(CubeCoordinate(1, 2)))
        assertTrue(coords.contains(CubeCoordinate(0, 2)))
        assertTrue(coords.contains(CubeCoordinate(0, 1)))

        assertTrue(!coords.contains(CubeCoordinate(-1, 0)))
        assertTrue(!coords.contains(CubeCoordinate(0, -1)))
        assertTrue(!coords.contains(CubeCoordinate(1, -1)))
        assertTrue(!coords.contains(CubeCoordinate(2, -1)))
        assertTrue(!coords.contains(CubeCoordinate(3, -1)))
        assertTrue(!coords.contains(CubeCoordinate(3, 0)))
        assertTrue(!coords.contains(CubeCoordinate(3, 1)))
        assertTrue(!coords.contains(CubeCoordinate(3, 2)))
        assertTrue(!coords.contains(CubeCoordinate(2, 3)))
        assertTrue(!coords.contains(CubeCoordinate(1, 3)))
        assertTrue(!coords.contains(CubeCoordinate(0, 3)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 2)))
        assertTrue(!coords.contains(CubeCoordinate(-1, 1)))
    }

    @Test
    fun shouldReturnTrueWhenCheckParametersIsCalled() {
        assertTrue(TRAPEZOID.checkParameters(2, 2))
    }

}
