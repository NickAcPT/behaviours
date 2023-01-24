package io.github.nickacpt.behaviours.replay.model.metadata.def

import io.github.nickacpt.behaviours.replay.model.Replay
import io.github.nickacpt.behaviours.replay.model.metadata.ReplayMetadataProvider
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.JoinConfiguration
import net.kyori.adventure.text.format.NamedTextColor

object DebugReplayInformation : ReplayMetadataProvider<Unit> {
    override fun provideMetadata(replay: Replay) {
    }

    override fun provideDisplay(replay: Replay, metadata: Unit): Component? {
        return Component.join(
            JoinConfiguration.newlines(),
            listOf(Component.text("Debug information:", NamedTextColor.GRAY),
                *replay.recordables.flatMap { it.value }.map { it.javaClass.simpleName }.groupBy { it }
                    .map { (k, v) -> Component.text("$k: ${v.size}", NamedTextColor.GOLD) }.toTypedArray()
            )
        )
    }

}