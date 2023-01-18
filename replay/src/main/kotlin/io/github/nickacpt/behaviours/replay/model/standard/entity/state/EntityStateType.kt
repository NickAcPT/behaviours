package io.github.nickacpt.behaviours.replay.model.standard.entity.state

/**
 * Represents the type of entity state.
 */
sealed class EntityStateType {
    /**
     * Entity was spawned.
     */
    object Spawn : EntityStateType()
    /**
     * Entity was despawned.
     */
    object Despawn : EntityStateType()
    /**
     * Entity swung its hand.
     */
    data class SwingArm(val hand: ReplayEntityHand) : EntityStateType()
}