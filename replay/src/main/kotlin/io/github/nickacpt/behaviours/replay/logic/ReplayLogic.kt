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

class ReplayLogic<World : ReplayWorld,
        Viewer : ReplayViewer<World>,
        Entity : ReplayEntity,
        Platform : ReplayPlatform<World, Viewer, Entity>,
        System : ReplaySystem<World, Viewer, Entity, Platform>,
        Session : ReplaySession<World, Viewer, Entity, Platform, System>,
        ReplayerType : Replayer<World, Viewer, Entity, Platform, System>

        >(
    val system: System,
    val platform: Platform
) {

    fun onReplaySessionStart(
        session: ReplaySession<World, Viewer, Entity, Platform, System>,
        replay: Replay,
        replayer: ReplayerType,
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

    internal fun updateSessionStatusForViewers() {
        system.replaySessions.forEach { session ->
            session.viewers.forEach { viewer ->
                session.replayer.updateReplaySessionStateForViewer(session, viewer)
            }
        }
    }

    fun tickSessions() {
        system.replaySessions.forEach { session ->
            @Suppress("UNCHECKED_CAST") // We know that the session is of the correct type, it's also erased anyway so not like it matters
            (session as Session).tickSession()
        }
    }

    fun onReplayControlItemInteraction(
        session: Session,
        viewer: Viewer,
        controlItem: ReplayControlItemType,
        isRightClick: Boolean
    ) {

    }

    fun Session.tickSession() {

    }
}