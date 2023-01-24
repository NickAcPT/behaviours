package io.github.nickacpt.behaviours.replay.record

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer
import io.github.nickacpt.behaviours.replay.abstractions.ReplayWorld
import io.github.nickacpt.behaviours.replay.model.Recordable
import io.github.nickacpt.behaviours.replay.model.RecordedReplayEntity
import io.github.nickacpt.behaviours.replay.model.Replay
import io.github.nickacpt.behaviours.replay.model.metadata.ReplayMetadataKey
import io.github.nickacpt.behaviours.replay.model.standard.EndingRecordable
import io.github.nickacpt.behaviours.replay.model.standard.TickRecordable
import java.util.*

class ReplayRecorder<
        World : ReplayWorld,
        Viewer : ReplayViewer<World>,
        Entity : ReplayEntity,
        Platform : ReplayPlatform<World, Viewer, Entity>,
        System : ReplaySystem<World, Viewer, Entity, Platform>> internal constructor(
    val system: System,
    val entities: List<Entity>,
    val configuration: RecordingConfiguration = RecordingConfiguration()
) {

    var replay: Replay? = null
        private set

    private var recording = false
    private var tickCount: ULong? = null
    private val recordables = mutableMapOf<ULong, List<Recordable>>()
    private val currentRecordables = mutableListOf<Recordable>()

    init {
        if (configuration.autoStart) {
            startRecording()
        }
    }

    fun tick() {
        if (!recording) return
        addRecordable(TickRecordable(entities.associate { it.id to it.location }))

        if (currentRecordables.isNotEmpty()) {
            recordables[tickCount!!] = currentRecordables.toList()
            currentRecordables.clear()
        }
        tickCount = tickCount?.plus(1u)
    }

    fun addRecordable(recordable: Recordable) {
        if (!recording) return

        currentRecordables.add(recordable)
    }

    fun startRecording(): Boolean {
        if (recording) return false

        recording = true
        tickCount = 0u
        replay = initializeReplay()

        return true
    }

    fun stopRecording(): Boolean {
        if (!recording) return false

        addRecordable(EndingRecordable)

        // Tick one last time to add the ending recordable
        tick()

        recording = false
        tickCount = null

        return true
    }

    private fun initializeReplay(): Replay {
        val metadataMap = mutableMapOf<ReplayMetadataKey<out Any>, Any>()
        val id = UUID.randomUUID()
        val entitiesList = entities.map { RecordedReplayEntity(it) }

        val replay = Replay(id, entitiesList, metadataMap, recordables)

        metadataMap.putAll(system.provideReplayMetadata(replay))

        return replay
    }

}