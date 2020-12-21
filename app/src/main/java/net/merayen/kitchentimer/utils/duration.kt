package net.merayen.kitchentimer.utils

import kotlin.math.abs

fun durationToString(seconds: Int): String {
    val result = StringBuilder()

    if (abs(seconds) > 3600 * 24)
        result.append("${seconds / (3600 * 24)}d")

    if (abs(seconds) > 3600)
        result.append(" ${seconds / 3600 % 24}h")

    if (abs(seconds) > 60)
        result.append(" ${seconds / 60 % 60}m")

    result.append(" ${seconds % 60}s")

    return result.toString().trim()
}