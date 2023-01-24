package io.github.nickacpt.behaviours.replay.model.standard

import io.github.nickacpt.behaviours.replay.model.standard.location.RecordableLocation

data class TickRecordable(
    val entityPositions: Map<Int, RecordableLocation>
) : StandardRecordable()