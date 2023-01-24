package io.github.nickacpt.behaviours.replay.logic

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer
import io.github.nickacpt.behaviours.replay.abstractions.ReplayWorld
import io.github.nickacpt.behaviours.replay.model.standard.TickRecordable
import io.github.nickacpt.behaviours.replay.playback.Replayer
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
            this.doSessionTick()
        }

        if (state == ReplaySessionState.PLAYING) {
            currentTick++

            if (currentTick >= replay.duration) {
                currentTick = replay.duration
                state = ReplaySessionState.FINISHED
            }
        }
    }

    private fun Session.doSessionTick() {
        replay.recordables[currentTick]?.forEach { recordable ->
            // TODO: Abstract recordable playing
            if (recordable is TickRecordable) {
                recordable.entityPositions.forEach { (id, pos) ->
                    entityManager.entityMap[id]?.let { entityManager.updateEntityPosition(it, pos) }
                }
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
                session.currentTick = 0u
                ReplaySessionState.PAUSED
            }
            ReplayControlItemType.STEP_FORWARD -> TODO()
            ReplayControlItemType.INCREASE_SPEED -> TODO()
        }
    }

}