package io.github.nickacpt.behaviours.replay.playback

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform

interface Replayer<
        NativeItemStack,
        NativeViewer,
        NativeWorld,
        NativeEntity,
        Platform : ReplayPlatform<NativeItemStack, NativeViewer, NativeWorld, NativeEntity>,
        System : ReplaySystem<NativeItemStack, NativeViewer, NativeWorld, NativeEntity, Platform>> {
}