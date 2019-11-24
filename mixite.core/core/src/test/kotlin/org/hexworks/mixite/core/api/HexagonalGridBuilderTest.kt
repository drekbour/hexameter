package org.hexworks.mixite.core.api

import org.hexworks.mixite.core.api.HexagonOrientation.FLAT_TOP
import org.hexworks.mixite.core.api.HexagonalGridLayout.RECTANGULAR
import org.hexworks.mixite.core.api.HexagonalGridLayout.TRIANGULAR
import org.hexworks.mixite.core.api.contract.HexagonDataStorage
import org.hexworks.mixite.core.api.defaults.DefaultHexagonDataStorage
import org.hexworks.mixite.core.api.defaults.DefaultSatelliteData
import kotlin.test.*

class HexagonalGridBuilderTest {

    @Test
    fun shouldContainProperValuesWhenGettersAreCalled() {
        val builder = gridBuilder()

        assertEquals(GRID_HEIGHT, builder.getGridHeight())
        assertEquals(GRID_WIDTH,builder.getGridWidth())
        assertEquals(GRID_LAYOUT, builder.getGridLayout())
        assertEquals(RADIUS, builder.getRadius())
        assertEquals(ORIENTATION, builder.getOrientation())
    }

    @Test
    fun shouldBuildCorrectGrid() {
        val grid = gridBuilder().build()

        assertNotNull(grid.gridData)
        assertEquals(GRID_HEIGHT, grid.gridData.gridHeight)
        assertEquals(GRID_WIDTH, grid.gridData.gridWidth)
        assertEquals(GRID_LAYOUT, grid.gridData.gridLayout)
        assertEquals(RADIUS, grid.gridData.radius)
        assertEquals(ORIENTATION, grid.gridData.orientation)
        assertTrue(grid.containsCubeCoordinate(CubeCoordinate(1, 1)))
    }

    @Test
    fun shouldFailWithZeroRadius() {
        assertFailsWith<IllegalStateException> {
            gridBuilder()
                .setRadius(0.0)
                .build()
        }
    }


    @Test
    fun shouldFailBuildWhenSizeIsNotCompatibleWithLayout() {
        assertFailsWith<IllegalStateException> {
            gridBuilder()
                    .setGridLayout(TRIANGULAR)
                    .setGridWidth(9)
                    .setGridHeight(4)
                    .build()
        }
    }


    @Test
    fun shouldBuildWhenProperParametersArePresent() {
        val grid = gridBuilder().build()

        assertNotNull(grid)
    }

    @Test
    fun shouldAllowAnyDataStorage() {
        val storage: HexagonDataStorage<DefaultSatelliteData> = DefaultHexagonDataStorage()
        gridBuilder()
                .setHexagonDataStorage(storage)
                .build()

        assertTrue(storage.containsCoordinate(CubeCoordinate(1, 1)))
    }

    companion object {
        private const val GRID_HEIGHT = 9
        private const val GRID_WIDTH = 9
        private const val RADIUS = 30.0
        private val GRID_LAYOUT = RECTANGULAR
        private val ORIENTATION = FLAT_TOP

        private fun gridBuilder(): HexagonalGridBuilder<DefaultSatelliteData> =
                HexagonalGridBuilder<DefaultSatelliteData>()
                        .setGridHeight(GRID_HEIGHT)
                        .setGridLayout(GRID_LAYOUT)
                        .setGridWidth(GRID_WIDTH)
                        .setOrientation(ORIENTATION)
                        .setRadius(RADIUS)
    }

}
