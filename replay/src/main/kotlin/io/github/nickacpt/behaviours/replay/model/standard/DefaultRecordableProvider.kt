package io.github.nickacpt.behaviours.replay.model.standard

import io.github.nickacpt.behaviours.replay.model.Recordable

interface DefaultRecordableProvider<T: Recordable> {
    fun getDefault(): T
}
