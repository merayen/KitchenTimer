package net.merayen.kitchentimer.utils.formula

class CanNotParse : RuntimeException()

/**
 * Parses math formula text strings.
 * This is custom as heck.
 */
fun parse(text: String): Token {
    try {
        return Statement(0, text)
    } catch (e: Token.ParseFailed) { }

    try {
        return Expression(0, text)
    } catch (e: Token.ParseFailed) { }

    throw CanNotParse()
}