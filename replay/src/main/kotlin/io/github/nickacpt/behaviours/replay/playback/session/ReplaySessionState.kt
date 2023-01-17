package io.github.nickacpt.behaviours.replay.playback.session

/**
 * Represents the state of a [ReplaySession].
 */
enum class ReplaySessionState {
    /**
     * The state of the session when it is loading the replay.
     */
    LOADING,

    /**
     * The state of the session when it is paused.
     */
    PAUSED,

    /**
     * The state of the session when it is playing back the replay.
     */
    PLAYING,

    /**
     * The state of the session when it has finished playing back the replay.
     */
    FINISHED
}