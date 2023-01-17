package io.github.nickacpt.behaviours.replay.playback.session

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Represents the settings of a [ReplaySession].
 * @param currentPlaybackSpeed The current playback speed of the session. ( 1.0 = normal speed, meaning 20 ticks per second )
 * @param currentStepSize The current step size. ( default is 1 second )
 */
data class ReplaySessionSettings(
    val currentPlaybackSpeed: Double = 1.0,
    val currentStepSize: Duration = 1.seconds
)