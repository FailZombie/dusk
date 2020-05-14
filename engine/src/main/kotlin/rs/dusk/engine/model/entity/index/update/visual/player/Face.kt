package rs.dusk.engine.model.entity.index.update.visual.player

import rs.dusk.engine.model.entity.index.player.Player
import rs.dusk.engine.model.entity.index.update.Visual

/**
 * @author Greg Hibberd <greg@greghibberd.com>
 * @since April 25, 2020
 */
data class Face(var deltaX: Int = 0, var deltaY: Int = -1) : Visual

const val FACE_DIRECTION_MASK = 0x20

fun Player.flagFace() = visuals.flag(FACE_DIRECTION_MASK)

fun Player.getFace() = visuals.getOrPut(FACE_DIRECTION_MASK) { Face() }

fun Player.face(deltaX: Int = 0, deltaY: Int = -1) {
    val face = getFace()
    face.deltaX = deltaX
    face.deltaY = deltaY
    flagFace()
}