package rs.dusk.network.codec.game.encode.message

import rs.dusk.game.entity.character.player.render.PlayerRendering
import rs.dusk.network.codec.game.GameServiceMessage

/**
 * @author Tyluur <itstyluur@gmail.com>
 * @since December 18, 2020
 */
data class MapRegionMessage(
	val mapSize : Int,
	val forceReload : Boolean,
	val chunkX : Int,
	val chunkY : Int,
	val xteas : Array<IntArray>,
	val clientIndex : Int,
	val clientTile : Int,
	val sendLswp : Boolean,
	val render: PlayerRendering
) : GameServiceMessage {


}