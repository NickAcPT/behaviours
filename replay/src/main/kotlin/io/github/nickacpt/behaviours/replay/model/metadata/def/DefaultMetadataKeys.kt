package io.github.nickacpt.behaviours.replay.model.metadata.def

import io.github.nickacpt.behaviours.replay.model.metadata.ReplayMetadataKey

data class DefaultMetadataKeys(
    val recordingInformation: ReplayMetadataKey<ReplayRecordingInformation>,
)