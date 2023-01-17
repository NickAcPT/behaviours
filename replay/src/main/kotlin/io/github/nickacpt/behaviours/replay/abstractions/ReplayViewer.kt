package io.github.nickacpt.behaviours.replay.abstractions

import io.github.nickacpt.behaviours.replay.Replay
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.audience.ForwardingAudience
import java.util.*

/**
 * Represents a viewer on a [Replay].
 */
interface ReplayViewer : ForwardingAudience.Single {

    /**
     * The unique ID of the viewer.
     */
    val id: UUID

    /**
     * The world the viewer is in.
     */
    val world: ReplayWorld?

    /**
     * The audience used to send messages to the viewer.
     */
    val audience: Audience

    override fun audience(): Audience {
        return audience
    }
}