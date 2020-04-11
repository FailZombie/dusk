package org.redrune.network.rs.codec.game.decode

import org.redrune.core.network.model.message.Message

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since February 19, 2020
 */
data class WorldListRefreshMessage(val crc: Int) : Message