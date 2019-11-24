package org.hexworks.mixite.core.api

/**
 * Represents a right or left angle (60°) of a Hexagon rotation.
 * See: http://www.redblobgames.com/grids/hexagons/#rotation
 */
enum class RotationDirection(
        /**
         * Calculates a rotation (right or left).
         */
        internal val rotate: (CubeCoordinate) -> CubeCoordinate
) {

    RIGHT({ coord -> CubeCoordinate.fromCoordinates(-coord.gridZ, -coord.gridY) }),
    LEFT({ coord -> CubeCoordinate.fromCoordinates(-coord.gridY, -coord.gridX) });

    /**
     * Calculates a rotation (right or left) of `coord`.
     *
     * @param coord coordinate to rotate
     *
     * @return rotated coordinate
     */
    @Deprecated("", replaceWith = ReplaceWith("rotate"))
    fun calculateRotation(coord: CubeCoordinate): CubeCoordinate {
        return rotate(coord)
    }

}
