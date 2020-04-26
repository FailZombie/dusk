package rs.dusk.network.rs.codec.game.encode.message

import rs.dusk.core.network.model.message.Message

/**
 * @author Greg Hibberd <greg@greghibberd.com>
 * @since April 18, 2020
 */
data class MapRegionMessage(
    val chunkX: Int,
    val chunkY: Int,
    val forceReload: Boolean,
    val mapSize: Int,
    val xteas: Array<IntArray>,
    val positions: IntArray?,
    val location: Int?
) : Message {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MapRegionMessage

        if (chunkX != other.chunkX) return false
        if (chunkY != other.chunkY) return false
        if (forceReload != other.forceReload) return false
        if (mapSize != other.mapSize) return false
        if (!xteas.contentDeepEquals(other.xteas)) return false
        if (positions != null) {
            if (other.positions == null) return false
            if (!positions.contentEquals(other.positions)) return false
        } else if (other.positions != null) return false
        if (location != other.location) return false

        return true
    }

    override fun hashCode(): Int {
        var result = chunkX
        result = 31 * result + chunkY
        result = 31 * result + forceReload.hashCode()
        result = 31 * result + mapSize
        result = 31 * result + xteas.contentDeepHashCode()
        result = 31 * result + (positions?.contentHashCode() ?: 0)
        result = 31 * result + (location ?: 0)
        return result
    }

}