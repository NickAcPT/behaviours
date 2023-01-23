package io.github.nickacpt.behaviours.replay.logic

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer
import io.github.nickacpt.behaviours.replay.abstractions.ReplayWorld

class ReplayLogic<Viewer : ReplayViewer,
        World : ReplayWorld, Entity : ReplayEntity,
        Platform : ReplayPlatform<Viewer, World, Entity>,
        System : ReplaySystem<Viewer, World, Entity, Platform>>(
    val system: System,
    val platform: Platform
) {

    private fun registerRepeatingTask(delay: Long, task: () -> Unit) {
        platform.registerRepeatingTask(delay, task)
    }

    fun initialize() {
        registerRepeatingTask(5, this::updateSessionStatusForViewers)
    }

    private fun updateSessionStatusForViewers() {
        system.replaySessions.forEach { session ->
            session.viewers.forEach { viewer ->
                session.replayer.updateReplaySessionStateForViewer(session, viewer)
            }
        }
    }


}