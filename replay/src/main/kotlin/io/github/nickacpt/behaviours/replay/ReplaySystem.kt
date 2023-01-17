package io.github.nickacpt.behaviours.replay

import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer
import io.github.nickacpt.behaviours.replay.metadata.ReplayMetadataKey
import io.github.nickacpt.behaviours.replay.metadata.ReplayMetadataProvider
import io.github.nickacpt.behaviours.replay.playback.session.ReplaySession

/**
 * A class holding the state of a replay system.
 *
 * @param NativeItemStack The native NativeItemStack type of the platform.
 * @param Platform The platform in which the replay system exists.
 */
class ReplaySystem<NativeItemStack, NativeViewer, Platform : ReplayPlatform<NativeItemStack, NativeViewer>>(
    private val platform: Platform
) {
    private val registeredMetadataKeys: MutableMap<String, ReplayMetadataKey<*>> = mutableMapOf()

    /**
     * Registers a new metadata key.
     *
     * This key can be used to store data in a [Replay].
     * @param T The type of the metadata to be stored.
     * @param key The [String] key used to index the data.
     * @param clazz The Java Type of [T].
     */
    fun <T> registerMetadataKey(
        key: String,
        clazz: Class<T>,
        provider: ReplayMetadataProvider<T>? = null
    ): ReplayMetadataKey<T> {
        return ReplayMetadataKey(key, clazz, provider).also { registeredMetadataKeys[key] = it }
    }

    /**
     * Registers a new metadata key.
     *
     * This key can be used to store data in a [Replay].
     * @param T The type of the metadata to be stored.
     * @param key The [String] key used to index the data.
     */
    inline fun <reified T> registerMetadataKey(key: String, provider: ReplayMetadataProvider<T>? = null) =
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
    ): ReplaySession<NativeItemStack, NativeViewer, Platform, ReplaySystem<NativeItemStack, NativeViewer, Platform>> {
        return ReplaySession(this, replay, replayViewers)
    }

    /**
     * Creates a new [ReplaySession] for the given [Replay] and [NativeViewer]s.
     *
     * @param replay The [Replay] to create the session for.
     * @param replayViewers The [NativeViewer]s to create the session for.
     */
    fun createReplaySession(
        replay: Replay,
        replayViewers: List<NativeViewer>
    ): ReplaySession<NativeItemStack, NativeViewer, Platform, ReplaySystem<NativeItemStack, NativeViewer, Platform>> {
        return ReplaySession(this, replay, replayViewers.map { platform.convertIntoReplayViewer(it) })
    }

}