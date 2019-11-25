package org.hexworks.mixite.core.internal.impl

import org.hexworks.mixite.core.api.*
import org.hexworks.mixite.core.api.HexagonalGridLayout.RECTANGULAR
import org.hexworks.mixite.core.api.defaults.DefaultHexagonDataStorage
import org.hexworks.mixite.core.api.defaults.DefaultSatelliteData
import org.hexworks.mixite.core.internal.GridData
import kotlin.test.*

class HexagonalGridImplTest {

    private lateinit var grid: HexagonalGrid<DefaultSatelliteData>
    private lateinit var gridData: GridData

    @BeforeTest
    fun setUp() {
        gridData = GridData(ORIENTATION, RECTANGULAR, RADIUS, GRID_WIDTH, GRID_HEIGHT)
        grid = HexagonalGridImpl(gridData, DefaultHexagonDataStorage(), gridData.gridLayout.gridLayoutStrategy)
    }

    @Test
    fun shouldReturnHexagonsInProperIterationOrderWhenGetHexagonsIsCalled() {
        val expectedCoordinates = ArrayList<CubeCoordinate>()
//        val actualCoordinates = ArrayList<CubeCoordinate>()

        for (cubeCoordinate in gridData.gridLayout.gridLayoutStrategy.fetchGridCoordinates(gridData)) {
            expectedCoordinates.add(cubeCoordinate)
        }
        val actualCoordinates = grid.hexagons.map {h -> h.cubeCoordinate}
//        for (hexagon in grid.hexagons) {
//            actualCoordinates.add(hexagon.cubeCoordinate)
//        }

        assertEquals(expectedCoordinates, actualCoordinates)
    }

    @Test
    fun shouldReturnProperHexagonsWhenEachHexagonIsTestedInAGivenGrid() {
        val hexagons = grid.hexagons
        val expectedCoordinates = HashSet<String>()
        for (x in 0 until GRID_WIDTH) {
            for (y in 0 until GRID_HEIGHT) {
                val gridX = CoordinateConverter.convertOffsetCoordinatesToCubeX(x, y, ORIENTATION)
                val gridZ = CoordinateConverter.convertOffsetCoordinatesToCubeZ(x, y, ORIENTATION)
                expectedCoordinates.add("$gridX,$gridZ")
            }
        }
        var count = 0
        for (hexagon in hexagons) {
            expectedCoordinates.remove("${hexagon.gridX},${hexagon.gridZ}")
            count++
        }
        assertEquals(100, count)
        assertTrue(expectedCoordinates.isEmpty())
    }

    @Test
    fun shouldReturnProperHexagonsWhenGetHexagonsByAxialRangeIsCalled() {
        val expected = HashSet<Hexagon<DefaultSatelliteData>>()

        expected.add(grid.getByCubeCoordinate(CubeCoordinate(2, 3)).get())
        expected.add(grid.getByCubeCoordinate(CubeCoordinate(3, 3)).get())
        expected.add(grid.getByCubeCoordinate(CubeCoordinate(4, 3)).get())

        expected.add(grid.getByCubeCoordinate(CubeCoordinate(2, 4)).get())
        expected.add(grid.getByCubeCoordinate(CubeCoordinate(3, 4)).get())
        expected.add(grid.getByCubeCoordinate(CubeCoordinate(4, 4)).get())

        expected.add(grid.getByCubeCoordinate(CubeCoordinate(2, 5)).get())
        expected.add(grid.getByCubeCoordinate(CubeCoordinate(3, 5)).get())
        expected.add(grid.getByCubeCoordinate(CubeCoordinate(4, 5)).get())

        val actual = grid.getHexagonsByCubeRange(CubeCoordinate(GRID_X_FROM, GRID_Z_FROM), CubeCoordinate(GRID_X_TO, GRID_Z_TO))
        var count = 0

        val actuals = ArrayList<Hexagon<DefaultSatelliteData>>()
        for (hex in actual) {
            actuals.add(hex)
            count++
        }
        assertEquals(expected.size, count)
        for (hex in actuals) {
            expected.remove(hex)
        }
        assertTrue(expected.isEmpty())
    }

    @Test
    fun shouldReturnProperHexagonsWhenGetHexagonsByOffsetRangeIsCalled() {
        val expected = HashSet<Hexagon<DefaultSatelliteData>>()

        expected.add(grid.getByCubeCoordinate(CubeCoordinate(1, 3)).get())
        expected.add(grid.getByCubeCoordinate(CubeCoordinate(2, 3)).get())
        expected.add(grid.getByCubeCoordinate(CubeCoordinate(3, 3)).get())

        expected.add(grid.getByCubeCoordinate(CubeCoordinate(0, 4)).get())
        expected.add(grid.getByCubeCoordinate(CubeCoordinate(1, 4)).get())
        expected.add(grid.getByCubeCoordinate(CubeCoordinate(2, 4)).get())

        expected.add(grid.getByCubeCoordinate(CubeCoordinate(0, 5)).get())
        expected.add(grid.getByCubeCoordinate(CubeCoordinate(1, 5)).get())
        expected.add(grid.getByCubeCoordinate(CubeCoordinate(2, 5)).get())

        val actual = grid.getHexagonsByOffsetRange(GRID_X_FROM, GRID_X_TO, GRID_Z_FROM, GRID_Z_TO)
        var count = 0
        val actuals = ArrayList<Hexagon<DefaultSatelliteData>>()
        for (hex in actual) {
            actuals.add(hex)
            count++
        }
        assertEquals(expected.size, count)
        for (hex in actuals) {
            expected.remove(hex)
        }
        assertTrue(expected.isEmpty())
    }

    @Test
    fun shouldContainCoordinateWhenContainsCoorinateIsCalledWithProperParameters() {
        val gridX = 2
        val gridZ = 3
        assertTrue(grid.containsCubeCoordinate(CubeCoordinate(gridX, gridZ)))
    }

    @Test
    fun shouldReturnHexagonWhenGetByGridCoordinateIsCalledWithProperCoordinates() {
        val gridX = 2
        val gridZ = 3
        val hex = grid.getByCubeCoordinate(CubeCoordinate(gridX, gridZ))
        assertTrue(hex.isPresent)
    }

    @Test
    fun shouldBeEmptyWhenGetByGridCoordinateIsCalledWithInvalidCoordinates() {
        val gridX = 20
        val gridZ = 30
        val result = grid.getByCubeCoordinate(CubeCoordinate(gridX, gridZ))
        assertFalse(result.isPresent)
    }

    @Test
    fun shouldReturnHexagonWhenCalledWithProperCoordinates() {
        val x = 310.0
        val y = 255.0
        val hex = grid.getByPixelCoordinate(x, y).get()
        assertEquals(hex.gridX, 3)
        assertEquals(hex.gridZ, 5)
    }

    @Test
    fun shouldReturnHexagonWhenCalledWithProperCoordinates2() {
        val x = 300.0
        val y = 275.0
        val hex = grid.getByPixelCoordinate(x, y).get()
        assertEquals(hex.gridX, 3)
        assertEquals(hex.gridZ, 5)
    }

    @Test
    fun shouldReturnHexagonWhenCalledWithProperCoordinates3() {
        val x = 325.0
        val y = 275.0
        val hex = grid.getByPixelCoordinate(x, y).get()
        assertEquals(hex.gridX, 3)
        assertEquals(hex.gridZ, 5)
    }

    @Test
    fun shouldReturnProperNeighborsOfHexagonWhenHexIsInMiddle() {
        val hex = grid.getByCubeCoordinate(CubeCoordinate(3, 7)).get()
        val actual = grid.getNeighborsOf(hex).map { h -> h.cubeCoordinate }

        val expected = listOf(
                CubeCoordinate(3, 6),
                CubeCoordinate(4, 6),
                CubeCoordinate(4, 7),
                CubeCoordinate(3, 8),
                CubeCoordinate(2, 8),
                CubeCoordinate(2, 7))

        assertEquals(expected.size, actual.size)
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun shouldReturnProperNeighborsOfHexagonWhenHexIsOnTheEdge() {
        val hex = grid.getByCubeCoordinate(CubeCoordinate(5, 9)).get()
        val actual = grid.getNeighborsOf(hex).map { h -> h.cubeCoordinate }

        val expected = listOf(
                CubeCoordinate(5, 8),
                CubeCoordinate(4, 9))

        assertEquals(expected.size, actual.size)
        assertTrue(actual.containsAll(expected))
    }

    @Test
    fun shouldProperlyReturnGridLayoutWhenGetGridLayoutIsCalled() {
        assertEquals(RECTANGULAR, grid.gridData.gridLayout)
    }

    @Test
    fun shouldProperlyReturnGridWidthWhenGetGridWidthIsCalled() {
        assertEquals(GRID_WIDTH, grid.gridData.gridWidth)
    }

    @Test
    fun shouldProperlyReturnGridHeightWhenGetGridHeightIsCalled() {
        assertEquals(GRID_HEIGHT, grid.gridData.gridHeight)
    }

    companion object {
        private const val RADIUS = 30.0
        private const val GRID_WIDTH = 10
        private const val GRID_HEIGHT = 10
        private const val GRID_X_FROM = 2
        private const val GRID_X_TO = 4
        private const val GRID_Z_FROM = 3
        private const val GRID_Z_TO = 5
        private val ORIENTATION = HexagonOrientation.POINTY_TOP
    }
}
