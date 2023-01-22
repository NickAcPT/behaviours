package io.github.nickacpt.behaviours.replay.model

import io.github.nickacpt.behaviours.replay.metadata.ReplayMetadataKey
import java.util.*

/**
 * A Replay.
 *
 * This class represents a replay of many [Recordable] actions.
 * It also contains metadata about the replay, along with its ID.
 */
data class Replay(
    val id: UUID,
    val metadata: Map<ReplayMetadataKey<in Any>, Any>,
    val recordables: List<Recordable>
)
