package io.github.nickacpt.behaviours.replay.abstractions

import io.github.nickacpt.behaviours.replay.model.Replay
import java.util.*

/**
 * Represents a world that can be used to play and record a [Replay].
 */
interface ReplayWorld {
    /**
     * The uuid of the world.
     */
    val id: UUID
}