package rs.dusk.engine.path.obstruction

import rs.dusk.engine.model.entity.Direction
import rs.dusk.engine.model.entity.Size
import rs.dusk.engine.model.world.Tile
import rs.dusk.engine.model.world.map.collision.Collisions
import rs.dusk.engine.model.world.map.collision.block
import rs.dusk.engine.model.world.map.collision.check
import rs.dusk.engine.model.world.map.collision.clear
import rs.dusk.engine.path.ObstructionStrategy
import rs.dusk.engine.path.obstruction.MediumObstruction.Companion.getNorthCorner
import rs.dusk.engine.path.obstruction.MediumObstruction.Companion.getSouthCorner
import rs.dusk.utility.inject

/**
 * Checks for collision in the direction of movement for entities with any size
 * @author Greg Hibberd <greg@greghibberd.com>
 * @since May 18, 2020
 */
class LargeObstruction(val size: Size) : ObstructionStrategy {

    private val collisions: Collisions by inject()

    override fun obstructed(tile: Tile, direction: Direction): Boolean {
        val delta = direction.delta
        val inverse = direction.inverse()
        var offsetX = if (delta.x == 1) size.width else delta.x
        var offsetY = if (delta.y == 1) size.height else delta.y
        if (inverse.isCardinal()) {
            // Start
            if (collisions.check(tile.x + offsetX, tile.y + offsetY, tile.plane, getNorthCorner(inverse).block())) {
                return true
            }
            // End
            offsetX = if (delta.x == -1) -1 else size.width + (delta.x - 1)
            offsetY = if (delta.y == -1) -1 else size.height + (delta.y - 1)
            if (collisions.check(tile.x + offsetX, tile.y + offsetY, tile.plane, getSouthCorner(inverse).block())) {
                return true
            }
            // In between
            val s = if (delta.y == 0) size.height else size.width
            for (offset in 1 until s - 1) {
                offsetX = if (delta.x == 1) size.width else if (delta.x == -1) -1 else offset
                offsetY = if (delta.y == 1) size.height else if (delta.y == -1) -1 else offset
                if (!collisions.check(tile.x + offsetX, tile.y + offsetY, tile.plane, inverse.clear())) {
                    return true
                }
            }
        } else {
            // Diagonal
            if (collisions.check(tile.x + offsetX, tile.y + offsetY, tile.plane, inverse.block())) {
                return true
            }
            // Vertical
            for (offset in 1 until size.width) {
                val dx = offset - if (delta.x == 1) 0 else 1
                if (!collisions.check(tile.x + dx, tile.y + offsetY, tile.plane, inverse.vertical().clear())) {
                    return true
                }
            }
            // Horizontal
            for (offset in 1 until size.height) {
                val dy = offset - if (delta.y == 1) 0 else 1
                if (!collisions.check(tile.x + offsetX, tile.y + dy, tile.plane, inverse.horizontal().clear())) {
                    return true
                }
            }
        }

        return false
    }
}