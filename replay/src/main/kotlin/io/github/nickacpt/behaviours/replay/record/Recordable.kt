package io.github.nickacpt.behaviours.replay.record

import kotlin.time.Duration

/**
 * Represents something that can be recorded
 */
interface Recordable {
    /**
     * The timestamp of when this recordable happened
     */
    val timestamp: Duration
}