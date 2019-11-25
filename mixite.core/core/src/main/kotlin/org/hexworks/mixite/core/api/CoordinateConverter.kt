package org.hexworks.mixite.core.api

import kotlin.jvm.JvmStatic

/**
 * Utility class for converting coordinated from the offset system to
 * the cube one (the library uses the latter).
 */
class CoordinateConverter {
    init {
        throw UnsupportedOperationException("This utility class is not meant to be instantiated.")
    }

    companion object {

        /**
         * Calculates the cube X coordinate based on an offset coordinate pair.
         */
        @JvmStatic
        fun convertOffsetCoordinatesToCubeX(offsetX: Int, offsetY: Int, orientation: HexagonOrientation): Int {
            return if (HexagonOrientation.FLAT_TOP === orientation) offsetX else offsetX - offsetY / 2
        }

        /**
         * Calculates the cube Z coordinate based on an offset coordinate pair.
         */
        @JvmStatic
        fun convertOffsetCoordinatesToCubeZ(offsetX: Int, offsetY: Int, orientation: HexagonOrientation): Int {
            return if (HexagonOrientation.FLAT_TOP === orientation) offsetY - offsetX / 2 else offsetY
        }

        /**
         * Convert offset coordinates to a [CubeCoordinate]
         */
        @JvmStatic
        fun offsetToCubeCoordinate(offsetX: Int, offsetY: Int, orientation: HexagonOrientation): CubeCoordinate =
                if (HexagonOrientation.FLAT_TOP === orientation) {
                    CubeCoordinate( offsetX,  offsetY - offsetX / 2)
                } else {
                    CubeCoordinate( offsetX - offsetY / 2,  offsetY)
                }
    }

}
