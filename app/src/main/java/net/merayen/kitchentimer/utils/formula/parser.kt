package net.merayen.kitchentimer.utils.formula

/**
 * Parses math formula text strings.
 * This is custom as heck.
 */
fun parse(text: String): Expression {
    val result = Statement(0, text).onHandle()
    result.handle(text)
    return result
}