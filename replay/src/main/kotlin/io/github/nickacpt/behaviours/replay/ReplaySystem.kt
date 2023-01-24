package io.github.nickacpt.behaviours.replay

import io.github.nickacpt.behaviours.replay.abstractions.*
import io.github.nickacpt.behaviours.replay.logic.ReplayLogic
import io.github.nickacpt.behaviours.replay.model.Replay
import io.github.nickacpt.behaviours.replay.model.metadata.ReplayMetadataKey
import io.github.nickacpt.behaviours.replay.model.metadata.ReplayMetadataProvider
import io.github.nickacpt.behaviours.replay.model.metadata.def.DefaultMetadataKeys
import io.github.nickacpt.behaviours.replay.model.metadata.def.DefaultMetadataProvider
import io.github.nickacpt.behaviours.replay.playback.session.ReplaySession
import net.kyori.adventure.key.Key

/**
 * The [ReplaySystem] class holds the state of a replay system. It provides functionality to create
 * new [ReplaySession] and register new metadata keys for [Replay] objects.
 *
 * @param platform The [ReplayPlatform] to be used.
 * @param provideDefaultMetadata Whether to provide default metadata.
 *
 * @param ItemStack The native ItemStack type of the platform.
 * @param Platform The platform in which the replay system exists.
 */
class ReplaySystem<
        World : ReplayWorld,
        Viewer : ReplayViewer<World>,
        Entity : ReplayEntity,
        Platform : ReplayPlatform<World, Viewer, Entity>,
        >(
    internal val platform: Platform,
    provideDefaultMetadata: Boolean = true
) {
    private val registeredMetadataKeys: MutableMap<Key, ReplayMetadataKey<*>> = mutableMapOf()

    private val replaySessionsList =
        mutableListOf<ReplaySession<World, Viewer, Entity, Platform, ReplaySystem<World, Viewer, Entity, Platform>>>()

    val logic =
        ReplayLogic(this, platform)

    val replaySessions: List<ReplaySession<World, Viewer, Entity, Platform, ReplaySystem<World, Viewer, Entity, Platform>>>
        get() = replaySessionsList

    var defaultMetadataKeys: DefaultMetadataKeys? = null
        private set

    init {
        if (provideDefaultMetadata) {
            with(DefaultMetadataProvider) {
                defaultMetadataKeys = registerDefaultMetadata()
            }
        }
    }

    /**
     * Registers a new metadata key.
     * This key can be used to store data in a [Replay].
     *
     * @param T The type of the metadata to be stored.
     * @param key The [Key] used to index the data.
     * @param clazz The Java Type of [T].
     * @param provider The provider of the metadata.
     *
     * @return The registered [ReplayMetadataKey].
     */
    fun <T> registerMetadataKey(
        key: Key,
        clazz: Class<T>,
        provider: ReplayMetadataProvider<T>? = null
    ): ReplayMetadataKey<T> {
        return ReplayMetadataKey(key, clazz, provider).also { registeredMetadataKeys[key] = it }
    }

    /**
     * Registers a new metadata key.
     * This key can be used to store data in a [Replay].
     *
     * @param T The type of the metadata to be stored.
     * @param key The [Key] used to index the data.
     * @param provider The provider of the metadata.
     *
     * @return The registered [ReplayMetadataKey].
     */
    inline fun <reified T> registerMetadataKey(key: Key, provider: ReplayMetadataProvider<T>? = null) =
        registerMetadataKey(key, T::class.java, provider)

    /**
     * Creates a new [ReplaySession] for the given [Replay] and [ReplayViewer]s.
     *
     * @param replay The [Replay] to create the session for.
     * @param replayViewers The [ReplayViewer]s to create the session for.
     */
    fun createReplaySession(
        replay: Replay,
        replayViewers: List<Viewer>
    ): ReplaySession<World, Viewer, Entity, Platform,
            ReplaySystem<World, Viewer, Entity, Platform>> {
        val replayer = platform.createReplayer(this, replay)

        return ReplaySession(this, replay, replayViewers, replayer).also {
            replaySessionsList.add(it)
            logic.onReplaySessionStart(it, replay, replayer, replayViewers)
        }
    }

    fun initialize() {
        logic.platform.registerRepeatingTask(5, logic::updateSessionStatusForViewers)
    }
}