package org.hexworks.mixite.core.internal.impl.layoutstrategy

import org.hexworks.mixite.core.api.CubeCoordinate
import org.hexworks.mixite.core.api.HexagonalGridBuilder
import org.hexworks.mixite.core.api.contract.SatelliteData
import org.hexworks.mixite.core.internal.GridData

/**
 * Represents the method of creating a [org.hexworks.mixite.core.api.HexagonalGrid] corresponding to a given shape.
 */
interface GridLayoutStrategy {

    /**
     * Fetches a monotonically increasing (from left to right, top to bottom) Set of
     * grid coordinates corresponding to the shape of the requested grid layout.
     *
     * @param gridData intrinsic grid properties
     *
     * @return All [CubeCoordinate] for the given grid.
     */
    fun fetchGridCoordinates(gridData: GridData): Sequence<CubeCoordinate>

    /**
     * Checks whether the supplied parameters are valid for the given strategy.
     * *For example a hexagonal grid layout only works if the width equals to the height*
     */
    fun checkParameters(gridHeight: Int, gridWidth: Int): Boolean

    companion object {
        internal fun checkCommonCase(gridHeight: Int, gridWidth: Int) = gridHeight > 0 && gridWidth > 0
    }

}
