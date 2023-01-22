package io.github.nickacpt.behaviours.replay

import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer
import io.github.nickacpt.behaviours.replay.metadata.ReplayMetadataKey
import io.github.nickacpt.behaviours.replay.metadata.ReplayMetadataProvider
import io.github.nickacpt.behaviours.replay.metadata.def.DefaultMetadataProvider
import io.github.nickacpt.behaviours.replay.model.Replay
import io.github.nickacpt.behaviours.replay.playback.session.ReplaySession
import net.kyori.adventure.key.Key

/**
 * The [ReplaySystem] class holds the state of a replay system. It provides functionality to create
 * new [ReplaySession] and register new metadata keys for [Replay] objects.
 *
 * @param platform The [ReplayPlatform] to be used.
 * @param provideDefaultMetadata Whether to provide default metadata.
 *
 * @param NativeItemStack The native NativeItemStack type of the platform.
 * @param Platform The platform in which the replay system exists.
 */
class ReplaySystem<
        NativeItemStack,
        NativeViewer,
        NativeWorld, NativeEntity,
        Platform : ReplayPlatform<NativeItemStack, NativeViewer, NativeWorld, NativeEntity>,
        >(
    private val platform: Platform,
    provideDefaultMetadata: Boolean = true
) {
    private val registeredMetadataKeys: MutableMap<Key, ReplayMetadataKey<*>> = mutableMapOf()

    init {
        if (provideDefaultMetadata) {
            with(DefaultMetadataProvider) {
                registerDefaultMetadata()
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
        replayViewers: List<ReplayViewer>
    ): ReplaySession<NativeItemStack, NativeViewer, NativeWorld, NativeEntity, Platform,
            ReplaySystem<NativeItemStack, NativeViewer, NativeWorld, NativeEntity, Platform>> {
        val replayer = platform.createReplayer(this, replay)
        return ReplaySession(this, replay, replayViewers, replayer).also {
            replayer.prepareReplaySession(replay, it, replayViewers)
        }
    }

    /**
     * Creates a new [ReplaySession] for the given [Replay] and [NativeViewer]s.
     *
     * @param replay The [Replay] to create the session for.
     * @param replayViewers The [NativeViewer]s to create the session for.
     */
    @JvmName("createNativeViewerReplaySession")
    fun createReplaySession(
        replay: Replay,
        replayViewers: List<NativeViewer>
    ): ReplaySession<NativeItemStack, NativeViewer, NativeWorld, NativeEntity, Platform,
            ReplaySystem<NativeItemStack, NativeViewer, NativeWorld, NativeEntity, Platform>> {
        return createReplaySession(replay, replayViewers.map { platform.convertIntoReplayViewer(it) })
    }
}