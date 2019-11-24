package org.hexworks.mixite.core.internal

import org.hexworks.mixite.core.api.HexagonOrientation
import org.hexworks.mixite.core.api.HexagonalGridLayout
import kotlin.math.sqrt

/**
 * Immutable class which holds the shared data between the [org.hexworks.mixite.core.api.Hexagon]s of a
 * [org.hexworks.mixite.core.api.HexagonalGrid] and the HexagonalGrid's own immutable properties.
 */
data class GridData(val orientation: HexagonOrientation,
                    val gridLayout: HexagonalGridLayout,
                    val radius: Double,
                    val gridWidth: Int,
                    val gridHeight: Int) {

    val hexagonHeight: Double
    val hexagonWidth: Double
    val innerRadius: Double

    init {
        innerRadius = calculateWidth(radius) / 2
        if (orientation === HexagonOrientation.FLAT_TOP) {
            hexagonHeight = calculateHeight(radius)
            hexagonWidth = calculateWidth(radius)
        } else {
            hexagonHeight = calculateWidth(radius)
            hexagonWidth = calculateHeight(radius)
        }
    }

    private fun calculateHeight(radius: Double) = sqrt(3.0) * radius

    private fun calculateWidth(radius: Double) = radius * 3 / 2
}
