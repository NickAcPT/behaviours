package io.github.nickacpt.behaviours.replay

/**
 * A [Replay] Metadata Key.
 *
 * This key can be used to store extra metadata on the [Replay].
 *
 * @param T The type of the metadata to be stored.
 * @param key The [String] key of the metadata.
 * @param type The Java Type of [T].
 */
data class ReplayMetadataKey<T> internal constructor(val key: String, val type: Class<T>)