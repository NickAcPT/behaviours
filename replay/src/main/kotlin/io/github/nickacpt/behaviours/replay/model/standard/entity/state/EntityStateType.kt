package io.github.nickacpt.behaviours.replay.model.standard.entity.state

/**
 * The [EntityStateType] class represents the different types of states that an entity can be in.
 * These states include when an entity is spawned, despawned, or when it swings its arm.
 */
sealed class EntityStateType {
    /**
     * Represents the state when an entity is spawned in the world.
     */
    object Spawn : EntityStateType()
    /**
     * Represents the state when an entity is removed from the world.
     */
    object Despawn : EntityStateType()
    /**
     * Represents the state when an entity swings its arm.
     * @param hand The hand that the entity swung.
     */
    data class SwingArm(val hand: ReplayEntityHand) : EntityStateType()
}