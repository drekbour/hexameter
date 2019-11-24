package org.hexworks.mixite.core.api

import org.hexworks.mixite.core.api.HexagonOrientation.POINTY_TOP
import org.hexworks.mixite.core.api.HexagonalGridLayout.RECTANGULAR
import org.hexworks.mixite.core.api.contract.HexagonDataStorage
import org.hexworks.mixite.core.api.contract.SatelliteData
import org.hexworks.mixite.core.api.defaults.DefaultHexagonDataStorage
import org.hexworks.mixite.core.internal.GridData
import org.hexworks.mixite.core.internal.impl.HexagonalGridCalculatorImpl
import org.hexworks.mixite.core.internal.impl.HexagonalGridImpl

/**
 *
 * Builder for a [HexagonalGrid].
 * Can be used to build a [HexagonalGrid].
 * Mandatory parameters are:
 *
 *  * width of the grid
 *  * height of the grid
 *  * radius of a [Hexagon]
 *
 * Defaults for orientation and grid layout are POINTY_TOP and RECTANGULAR.
 */
class HexagonalGridBuilder<T : SatelliteData> {
    private var gridWidth = 0
    private var gridHeight = 0
    private var radius = 0.0
    private var hexagonDataStorage: HexagonDataStorage<T>? = null
    private var orientation = POINTY_TOP
    private var gridLayout = RECTANGULAR

    /**
     * Builds a [HexagonalGrid] using the parameters supplied.
     * Throws [HexagonalGridCreationException] if not all mandatory parameters
     * are filled and/or they are not valid. In both cases you will be supplied with
     * a [HexagonalGridCreationException] detailing the cause of failure.
     *
     * @return [HexagonalGrid]
     */
    fun build(): HexagonalGrid<T> {
        checkParameters()
        val gridData = GridData(orientation, gridLayout, radius, gridWidth, gridHeight)
        return HexagonalGridImpl(gridData, hexagonDataStorage ?: DefaultHexagonDataStorage(), gridLayout.gridLayoutStrategy)
    }

    /**
     * Creates a [HexagonalGridCalculator] for your [HexagonalGrid].
     *
     * @param hexagonalGrid grid
     *
     * @return calculator
     */
    fun <T : SatelliteData> buildCalculatorFor(hexagonalGrid: HexagonalGrid<T>): HexagonalGridCalculator<T> {
        return HexagonalGridCalculatorImpl(hexagonalGrid)
    }

    /**
     * Sets the radius of the [Hexagon]s contained in the resulting [HexagonalGrid].
     *
     * @param radius in pixels
     *
     * @return this [HexagonalGridBuilder]
     */
    fun setRadius(radius: Double) = apply { this.radius = radius }

    /**
     * Mandatory parameter. Sets the number of [Hexagon]s in the horizontal direction.
     *
     * @param gridWidth grid width
     *
     * @return this [HexagonalGridBuilder]
     */
    fun setGridWidth(gridWidth: Int) = apply { this.gridWidth = gridWidth }

    /**
     * Mandatory parameter. Sets the number of [Hexagon]s in the vertical direction.
     *
     * @param gridHeight grid height
     *
     * @return this [HexagonalGridBuilder]
     */
    fun setGridHeight(gridHeight: Int) = apply { this.gridHeight = gridHeight }

    /**
     * Sets the [HexagonOrientation] used in the resulting [HexagonalGrid].
     * If it is not set [POINTY_TOP] will be used.
     *
     * @param orientation orientation
     *
     * @return this [HexagonalGridBuilder]
     */
    fun setOrientation(orientation: HexagonOrientation) = apply { this.orientation = orientation }

    /**
     * Sets the backing [HexagonDataStorage] used by the resulting [HexagonalGrid].
     * If it is not set, [DefaultHexagonDataStorage] wil be used
     */
    fun setHexagonDataStorage(hexagonDataStorage: HexagonDataStorage<T>) = apply { this.hexagonDataStorage = hexagonDataStorage }

    /**
     * Sets the [HexagonalGridLayout] which will be used when creating the [HexagonalGrid].
     * If it is not set [RECTANGULAR] will be assumed.
     *
     * @param gridLayout layout
     *
     * @return this [HexagonalGridBuilder].
     */
    fun setGridLayout(gridLayout: HexagonalGridLayout) = apply { this.gridLayout = gridLayout }

    @Deprecated("To be removed")
    fun getRadius() = radius

    @Deprecated("To be removed")
    fun getGridWidth() = gridWidth

    @Deprecated("To be removed")
    fun getGridHeight() = gridHeight

    @Deprecated("To be removed")
    fun getOrientation() = orientation

    @Deprecated("To be removed")
    fun getGridLayout() =  gridLayout

    private fun checkParameters() {
        if (radius <= 0) {
            throw IllegalStateException("Radius $radius must be greater than 0.")
        }
        if (!gridLayout.checkParameters(gridHeight, gridWidth)) {
            throw IllegalStateException("Width: $gridWidth and height: $gridHeight is not valid for: ${gridLayout.name} layout.")
        }
    }
}
