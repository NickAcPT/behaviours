package io.github.nickacpt.behaviours.replay.model

import kotlin.time.Duration

/**
 * Represents something that can be recorded.
 */
abstract class Recordable {
    /**
     * The timestamp of when this recordable happened
     */
    var timestamp: Duration = Duration.ZERO
}