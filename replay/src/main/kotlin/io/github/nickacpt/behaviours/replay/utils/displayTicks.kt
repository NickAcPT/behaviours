package io.github.nickacpt.behaviours.replay.utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.JoinConfiguration

private const val TICKS_PER_SECOND = 20uL
private const val SECONDS_PER_MINUTE = 60uL
private const val MINUTES_PER_HOUR = 60uL

/**
 * Formats the given number of ticks into hours, minutes and seconds.
 *
 * @param ticks The number of ticks to format
 * @return The formatted time in hh:mm:ss format
 */
fun displayTicks(ticks: ULong): Component {
    val ticksPerMinute = TICKS_PER_SECOND * SECONDS_PER_MINUTE

    // Convert ticks to minutes
    val minutes = ticks / ticksPerMinute
    // Get the remaining seconds by using the modulo operator
    val seconds = (ticks % ticksPerMinute) / TICKS_PER_SECOND

    // Calculate the number of hours from the total number of minutes
    val hours = minutes / MINUTES_PER_HOUR

    // Calculate the remaining minutes after the hours have been extracted
    val finalMinutes = minutes % MINUTES_PER_HOUR

    val timeComponents = buildList {
        if (hours > 0u) add(hours)
        add(finalMinutes)
        add(seconds)
    }

    return Component.join(
        JoinConfiguration.separator(Component.text(":")),
        timeComponents.map { Component.text("%02d".format(it.toLong())) }
    )
}
