package io.github.nickacpt.behaviours.replay.model

import io.github.nickacpt.behaviours.replay.model.metadata.ReplayMetadataKey
import io.github.nickacpt.behaviours.replay.model.metadata.ReplayMetadataProvider
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.JoinConfiguration
import java.util.*

/**
 * A Replay.
 *
 * This class represents a replay of many [Recordable] actions.
 * It also contains metadata about the replay, along with its ID.
 */
data class Replay(
    val id: UUID,
    val entities: List<RecordedReplayEntity>,
    val metadata: MutableMap<ReplayMetadataKey<out Any>, out Any>,
    val recordables: Map<ULong, List<Recordable>>
) {
    val duration: ULong by lazy { recordables.keys.max() }

    operator fun <T> get(key: ReplayMetadataKey<in T>): T? {
        // This cast is not useless, it's required to make the compiler happy
        @Suppress(
            "USELESS_CAST",
            "UNCHECKED_CAST"
        )
        return metadata[key as ReplayMetadataKey<*>] as? T?
    }

    operator fun <T : Any> set(key: ReplayMetadataKey<in T>, value: T) {
        @Suppress(
            "UNCHECKED_CAST"
        )
        (metadata as MutableMap<ReplayMetadataKey<*>, Any>)[key] = value
    }

    fun computeDisplayLore(): Component {
        return Component.join(
            JoinConfiguration.separator(
                Component.newline().append(Component.newline())
            ),
            metadata.map { (key, value) ->
                @Suppress("UNCHECKED_CAST")
                (key.provider as? ReplayMetadataProvider<Any>)?.provideDisplay(this, value)
            }.filterNotNull()
        )
    }
}