package io.github.nickacpt.behaviours.replay

import io.github.nickacpt.behaviours.replay.record.Recordable
import java.util.*

/**
 * A Replay.
 *
 * This class represents a replay of many [Recordable] actions.
 * It also contains metadata about the replay, along with its ID.
 */
data class Replay(
    val id: UUID,
    val metadata: Map<ReplayMetadataKey<*>, *>,
    val recordables: List<Recordable>
)
