package io.github.nickacpt.behaviours.replay.metadata.def

import io.github.nickacpt.behaviours.replay.model.Replay
import io.github.nickacpt.behaviours.replay.metadata.ReplayMetadataProvider
import net.kyori.adventure.text.Component
import java.time.Instant
import java.time.format.DateTimeFormatter

data class ReplayRecordingInformation(
    val recordingStarted: Instant
) {
    companion object : ReplayMetadataProvider<ReplayRecordingInformation> {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        override fun provideMetadata(replay: Replay): ReplayRecordingInformation = ReplayRecordingInformation(
            Instant.now()
        )

        override fun provideDisplay(replay: Replay, metadata: ReplayRecordingInformation): Component {
            return Component.text(formatter.format(metadata.recordingStarted))
        }
    }
}