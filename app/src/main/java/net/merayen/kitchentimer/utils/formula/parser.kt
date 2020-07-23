package net.merayen.kitchentimer.utils.formula

class CanNotParse : RuntimeException()

/**
 * Parses math formula text strings.
 * This is custom as heck.
 */
fun parse(text: String): Token {
    val result: Token = try {
        Statement(0, text)
    } catch (e: Token.ParseFailed) {
        try {
            Expression(0, text)
        } catch (e: Token.ParseFailed) {
            throw CanNotParse()
        }
    }

    if (result.pos != text.length)
        throw CanNotParse()

    return result
}