package io.github.nickacpt.behaviours.replay.abstractions

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.model.Replay
import io.github.nickacpt.behaviours.replay.playback.Replayer

/**
 * Represents a platform that can be used to play and record a [Replay].
 * @param NativeItemStack The native ItemStack type of the platform.
 */
interface ReplayPlatform<NativeItemStack, NativeViewer, NativeWorld> {

    /* ItemStack */
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
    /* ItemStack */

    /* Viewer */
    /**
     * Converts a [NativeViewer] into a [ReplayViewer].
     * @param viewer The viewer to convert.
     * @return The converted viewer.
     */
    fun convertIntoReplayViewer(viewer: NativeViewer): ReplayViewer

    /**
     * Converts a [ReplayViewer] into a [NativeViewer].
     * @param viewer The viewer to convert.
     * @return The converted viewer.
     */
    fun convertIntoPlatformViewer(viewer: ReplayViewer): NativeViewer?
    /* Viewer */

    /* World */
    /**
     * Converts a [NativeWorld] into a [ReplayWorld].
     * @param world The world to convert.
     * @return The converted world.
     */
    fun convertIntoReplayWorld(world: NativeWorld): ReplayWorld

    /**
     * Converts a [ReplayWorld] into a [NativeWorld].
     * @param world The world to convert.
     * @return The converted world.
     */
    fun convertIntoPlatformWorld(world: ReplayWorld): NativeWorld?

    /**
     * Create a replayer for this platform.
     *
     * @param replaySystem The replay system in use.
     * @param replay The replay to play.
     */
    fun <Platform : ReplayPlatform<NativeItemStack, NativeViewer, NativeWorld>,
            System : ReplaySystem<NativeItemStack, NativeViewer, NativeWorld, Platform>> createReplayer(
        replaySystem: System,
        replay: Replay
    ): Replayer<NativeItemStack, NativeViewer, NativeWorld, Platform, System>
    /* World */
}
