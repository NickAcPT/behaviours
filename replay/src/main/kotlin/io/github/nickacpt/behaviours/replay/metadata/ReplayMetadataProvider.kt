package io.github.nickacpt.behaviours.replay.metadata

import io.github.nickacpt.behaviours.replay.Replay
import net.kyori.adventure.text.Component

/**
 * A provider for a [ReplayMetadataKey].
 *
 * This is used to display the metadata in a user-friendly way on the [Replay] list interface.
 * @param T The type of the metadata to be stored.
 */
interface ReplayMetadataProvider<T> {

    /**
     * Provides the metadata to the replay.
     *
     * This is used when recording the [Replay] to store the metadata.
     * @param replay The [Replay] to provide the metadata for.
     * @return The metadata to be stored.
     */
    fun provideMetadata(replay: Replay): T?

    /**
     * Provides the display data for the given metadata.
     *
     * This metadata can include multiple lines, and will be displayed on the [Replay] list interface,
     * in order of the registered providers.
     *
     * All user-visible metadata lines will be separated by an empty line.
     *
     * @param metadata The value of the metadata to be displayed.
     * @return The display data.
     */
    fun provideDisplay(replay: Replay, metadata: T): Component
}