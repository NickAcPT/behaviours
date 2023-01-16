package io.github.nickacpt.behaviours.replay.abstractions

import io.github.nickacpt.behaviours.replay.Replay

/**
 * Represents a platform that can be used to play and record a [Replay].
 * @param NativeItemStack The native ItemStack type of the platform.
 */
interface ReplayPlatform<NativeItemStack> {

    /**
     * Converts a [ReplayItemStack] into a [NativeItemStack].
     *
     * @param stack The stack to convert.
     * @return The converted stack.
     */
    fun convertIntoPlatformStack(stack: ReplayItemStack): NativeItemStack

    /**
     * Converts a [NativeItemStack] into a [ReplayItemStack].
     *
     * @param stack The stack to convert.
     * @return The converted stack.
     */
    fun convertIntoReplayStack(stack: NativeItemStack): ReplayItemStack

}
