package io.github.nickacpt.behaviours.replay.items

/**
 * An enum that represents the different types of ReplayControlItems.
 */
enum class ReplayControlItemType {
    /**
     * The item representing the action of decreasing playback speed.
     */
    DECREASE_SPEED,

    /**
     * The item representing the action of stepping backwards in the replay playback.
     */
    STEP_BACKWARDS,

    /**
     * The item representing the action of pausing the replay playback.
     */
    PAUSE,

    /**
     * The item representing the action of resuming the replay playback.
     */
    RESUME,

    /**
     * The item representing the action of restarting the replay playback.
     */
    RESTART,

    /**
     * The item representing the action of stepping forwards in the replay playback.
     */
    STEP_FORWARD,

    /**
     * The item representing the action of increasing playback speed.
     */
    INCREASE_SPEED
}