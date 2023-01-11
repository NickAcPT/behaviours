package io.github.nickacpt.behaviours.replay

import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.metatada.ReplayMetadataKey

/**
 * A class holding the state of a replay system.
 *
 * @param ItemStack The native ItemStack type of the platform.
 * @param Platform The platform in which the replay system exists.
 */
class ReplaySystem<ItemStack, Platform : ReplayPlatform<ItemStack>>(internal val platform: ReplayPlatform<ItemStack>) {
    private val registeredMetadataKeys: MutableMap<String, ReplayMetadataKey<*>> = mutableMapOf()

    /**
     * Registers a new metadata key.
     *
     * This key can be used to store data in a Replay.
     * @param T The type of the metadata to be stored.
     * @param key The [String] key used to index the data.
     * @param clazz The Java Type of [T].
     */
    fun <T> registerMetadataKey(key: String, clazz: Class<T>): ReplayMetadataKey<T> {
        return ReplayMetadataKey(key, clazz).also { registeredMetadataKeys[key] = it }
    }

    /**
     * Registers a new metadata key.
     *
     * This key can be used to store data in a Replay.
     * @param T The type of the metadata to be stored.
     * @param key The [String] key used to index the data.
     */
    inline fun <reified T> registerMetadataKey(key: String) = registerMetadataKey(key, T::class.java)
}