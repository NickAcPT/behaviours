package io.github.nickacpt.behaviours.replay.metadata.def

import io.github.nickacpt.behaviours.replay.ReplaySystem
import net.kyori.adventure.key.Key

internal object DefaultMetadataProvider {
    private const val METADATA_KEY = "replay_system"

    internal fun ReplaySystem<*, *, *, *, *>.registerDefaultMetadata() {
        registerMetadataKey(Key.key(METADATA_KEY, "recording_information"), ReplayRecordingInformation)
    }
}