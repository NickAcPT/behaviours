package io.github.nickacpt.behaviours.replay.replayer

import io.github.nickacpt.behaviours.replay.Replay
import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer

/**
 * Represents a playback session of a [Replay].
 */
class ReplaySession<
        NativeItemStack,
        Platform : ReplayPlatform<NativeItemStack>,
        System : ReplaySystem<Platform, NativeItemStack>
        >(
    val system: System,
    val replay: Replay,
    val settings: ReplaySessionSettings = ReplaySessionSettings(),
    val viewers: List<ReplayViewer>
)