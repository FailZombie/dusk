package rs.dusk.world.entity.player

import rs.dusk.core.network.model.session.Session
import rs.dusk.engine.event.Event
import rs.dusk.engine.event.EventCompanion
import rs.dusk.engine.model.entity.index.player.Player

/**
 * @param player Unconnected player save
 */
data class PlayerSpawn(val player: Player, val name: String, val session: Session? = null) : Event<Player>() {
    companion object : EventCompanion<PlayerSpawn>
}