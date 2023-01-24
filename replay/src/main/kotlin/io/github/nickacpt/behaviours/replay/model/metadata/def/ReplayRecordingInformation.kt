package io.github.nickacpt.behaviours.replay.model.metadata.def

import io.github.nickacpt.behaviours.replay.model.Replay
import io.github.nickacpt.behaviours.replay.model.metadata.ReplayMetadataProvider
import io.github.nickacpt.behaviours.replay.utils.displayTicks
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.JoinConfiguration
import net.kyori.adventure.text.format.NamedTextColor
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class ReplayRecordingInformation(
    val recordingStarted: Instant
) {
    companion object : ReplayMetadataProvider<ReplayRecordingInformation> {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            .withZone(ZoneId.of("UTC"))

        override fun provideMetadata(replay: Replay): ReplayRecordingInformation = ReplayRecordingInformation(
            Instant.now()
        )

        override fun provideDisplay(replay: Replay, metadata: ReplayRecordingInformation): Component {
            return Component.join(
                JoinConfiguration.newlines(),
                Component.text(formatter.format(metadata.recordingStarted)),
                Component.text("Duration: ").append(displayTicks(replay.duration))
            ).color(NamedTextColor.GRAY)
        }
    }
}