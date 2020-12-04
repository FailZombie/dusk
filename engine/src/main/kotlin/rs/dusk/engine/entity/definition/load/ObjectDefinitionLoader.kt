package rs.dusk.engine.entity.definition.load

import rs.dusk.cache.definition.decoder.ObjectDecoder
import rs.dusk.engine.TimedLoader
import rs.dusk.engine.entity.definition.ObjectDefinitions
import rs.dusk.engine.io.jackson.JacksonIO

class ObjectDefinitionLoader(private val decoder: ObjectDecoder, private val io: JacksonIO) :
    TimedLoader<ObjectDefinitions>("object definition") {

    override fun load(args: Array<out Any?>): ObjectDefinitions {
        val path = args[0] as String
        val data: Map<String, Map<String, Any>> = io.load(path)
        val names = data.map { it.value["id"] as Int to it.key }.toMap()
        count = names.size
        return ObjectDefinitions(decoder, data, names)
    }

}