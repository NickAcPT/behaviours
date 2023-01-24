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
import java.time.Duration
import java.time.Instant
import java.util.*
import kotlin.time.toKotlinDuration

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
    private var recordingStartTime: Instant? = null
    private val recordables = mutableListOf<Recordable>()

    init {
        if (configuration.autoStart) {
            startRecording()
        }
    }

    fun tick() {
        if (!recording) return

        addRecordable(TickRecordable(entities.associate { it.id to it.location }))
    }

    fun addRecordable(recordable: Recordable) {
        if (!recording) return

        recordable.timestamp = Duration.between(recordingStartTime, Instant.now()).toKotlinDuration()
        recordables.add(recordable)
    }

    fun startRecording(): Boolean {
        if (recording) return false

        recording = true
        recordingStartTime = Instant.now()
        replay = initializeReplay()

        return true
    }

    fun stopRecording(): Boolean {
        if (!recording) return false

        addRecordable(EndingRecordable())

        recording = false
        recordingStartTime = null

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