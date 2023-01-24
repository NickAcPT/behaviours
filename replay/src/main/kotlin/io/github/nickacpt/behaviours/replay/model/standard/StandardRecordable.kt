package io.github.nickacpt.behaviours.replay.model.standard

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.model.Recordable
import io.github.nickacpt.behaviours.replay.playback.Replayer

/**
 * Represents a recordable that can be recorded by the [ReplaySystem].
 *
 * All recordables implementing this interface should be implemented in a way that they can be
 * replayed by all platforms with a [Replayer].
 */
abstract class StandardRecordable : Recordable

