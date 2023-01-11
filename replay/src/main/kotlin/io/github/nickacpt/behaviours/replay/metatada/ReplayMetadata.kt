package io.github.nickacpt.behaviours.replay.metatada

data class ReplayMetadata<T> internal constructor(val key: ReplayMetadataKey<T>, val value: T)
