package io.github.nickacpt.behaviours.replay.logic

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer
import io.github.nickacpt.behaviours.replay.abstractions.ReplayWorld
import io.github.nickacpt.behaviours.replay.model.Replay
import io.github.nickacpt.behaviours.replay.playback.Replayer
import io.github.nickacpt.behaviours.replay.playback.session.ReplaySession
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor

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

    fun onReplaySessionStart(
        session: ReplaySession<Viewer, World, Entity, Platform, System>,
        replay: Replay,
        replayer: Replayer<Viewer, World, Entity, Platform, System>,
        replayViewers: List<Viewer>,
    ) {
        session.initialize(replayer.prepareReplaySession(replay, session, replayViewers))

        // Debug Only
        session.sendMessage(Component.text("Replay", NamedTextColor.GOLD).hoverEvent(replay.computeDisplayLore()))

        replayViewers.forEach { viewer ->
            replayer.updateReplaySessionViewerControls(session, viewer)
        }

        session.entityManager.also {
            replay.entities.forEach { entity ->
                it.spawnEntity(entity, entity.firstPosition)
            }
        }
    }

    private fun updateSessionStatusForViewers() {
        system.replaySessions.forEach { session ->
            session.viewers.forEach { viewer ->
                session.replayer.updateReplaySessionStateForViewer(session, viewer)
            }
        }
    }


}