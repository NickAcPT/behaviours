package io.github.nickacpt.behaviours.replay.abstractions

/**
 * Represents an entity that can be recorded.
 */
interface ReplayEntity {
    /**
     * The unique index-based id of this entity.
     */
    val id: Int
}