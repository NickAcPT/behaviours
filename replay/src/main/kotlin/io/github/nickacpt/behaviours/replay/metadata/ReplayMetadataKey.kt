package io.github.nickacpt.behaviours.replay.metadata

import io.github.nickacpt.behaviours.replay.Replay

/**
 * A [Replay] Metadata Key.
 *
 * This key can be used to store extra metadata on the [Replay].
 *
 * @param T The type of the metadata to be stored.
 * @param key The [String] key of the metadata.
 * @param type The Java Type of [T].
 * @param provider The [ReplayMetadataProvider] for this key.
 */
data class ReplayMetadataKey<T> internal constructor(
    val key: String,
    val type: Class<T>,
    val provider: ReplayMetadataProvider<T>? = null
)

