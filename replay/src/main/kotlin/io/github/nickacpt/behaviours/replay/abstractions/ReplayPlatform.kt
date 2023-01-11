package io.github.nickacpt.behaviours.replay.abstractions

/**
 * Represents a platform that can be used to replay a Replay.
 * @param ItemStack The native ItemStack type of the platform.
 */
interface ReplayPlatform<ItemStack> {

    fun intoPlatformStack(stack: ReplayItemStack): ItemStack

}
