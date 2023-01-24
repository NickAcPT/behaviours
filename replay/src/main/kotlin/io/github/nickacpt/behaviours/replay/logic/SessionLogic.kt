package io.github.nickacpt.behaviours.replay.logic

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer
import io.github.nickacpt.behaviours.replay.abstractions.ReplayWorld
import io.github.nickacpt.behaviours.replay.model.Recordable
import io.github.nickacpt.behaviours.replay.playback.Replayer
import io.github.nickacpt.behaviours.replay.playback.recordables.RecordablePlayer
import io.github.nickacpt.behaviours.replay.playback.session.ReplaySession
import io.github.nickacpt.behaviours.replay.playback.session.ReplaySessionState

class SessionLogic<World : ReplayWorld,
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

    fun Session.tick() {
        if (state == ReplaySessionState.LOADING) {
            entityManager.also {
                replay.entities.forEach { entity ->
                    it.entityMap[entity.id] = it.spawnEntity(entity, entity.firstPosition)
                }
            }
            state = ReplaySessionState.PAUSED
        }

        if (state == ReplaySessionState.PLAYING) {
            doSessionTick()
        }

        if (state == ReplaySessionState.PLAYING) {
            currentFractionalTick += settings.currentPlaybackSpeed

            if (currentFractionalTick >= 1.0) {
                //TODO Make sure we handle all in-between ticks
                while (currentFractionalTick >= 1.0) {
                    currentFractionalTick--
                    currentTick++
                    doSessionTick()
                }
            }

            if (currentTick >= replay.duration) {
                currentTick = replay.duration
                state = ReplaySessionState.FINISHED
            }
        }
    }

    private fun Session.doSessionTick(tick: ULong = currentTick) {

        replay.recordables[tick]?.forEach { recordable ->
            @Suppress("UNCHECKED_CAST") val player: RecordablePlayer<World, Viewer, Entity, Platform, System, Session, in Recordable> =
                system.provideRecordablePlayer(recordable.javaClass) as? RecordablePlayer<World, Viewer, Entity, Platform, System, Session, in Recordable>
                    ?: return@forEach

            with(player) {
                this@doSessionTick.play(tick, recordable)
            }
        }
    }

    fun handleControlItemInteraction(
        session: Session,
        viewer: Viewer,
        controlItem: ReplayControlItemType,
        rightClick: Boolean
    ) {
        session.state = when (controlItem) {
            ReplayControlItemType.RESUME -> ReplaySessionState.PLAYING
            ReplayControlItemType.DECREASE_SPEED -> TODO()
            ReplayControlItemType.STEP_BACKWARDS -> TODO()
            ReplayControlItemType.PAUSE -> ReplaySessionState.PAUSED
            ReplayControlItemType.RESTART -> {
                // Reset the session to the beginning and tick it once
                session.currentTick = 0u
                session.doSessionTick()

                ReplaySessionState.PAUSED
            }

            ReplayControlItemType.STEP_FORWARD -> TODO()
            ReplayControlItemType.INCREASE_SPEED -> TODO()
        }
    }

}