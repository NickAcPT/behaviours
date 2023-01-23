package io.github.nickacpt.behaviours.replay.model.metadata.def

import io.github.nickacpt.behaviours.replay.ReplaySystem
import net.kyori.adventure.key.Key

internal object DefaultMetadataProvider {
    private const val METADATA_KEY = "replay_system"
    private const val REC_INFO_VALUE = "recording_information"

    internal fun ReplaySystem<*, *, *, *>.registerDefaultMetadata(): DefaultMetadataKeys {
        return DefaultMetadataKeys(
            registerMetadataKey(Key.key(METADATA_KEY, REC_INFO_VALUE), ReplayRecordingInformation),
        )
    }
}