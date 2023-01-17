package io.github.nickacpt.behaviours.replay.playback.session

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform

interface Replayer<
        NativeItemStack,
        NativeViewer,
        NativeWorld,
        Platform : ReplayPlatform<NativeItemStack, NativeViewer, NativeWorld>,
        System : ReplaySystem<NativeItemStack, NativeViewer, NativeWorld, Platform>>