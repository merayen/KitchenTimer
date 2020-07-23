package net.merayen.kitchentimer.utils.formula

class CanNotParse : RuntimeException()

/**
 * Parses math formula text strings.
 * This is custom as heck.
 */
fun parse(text: String): Token {
    println("Trying out as Statement")
    var result: Token

    try {
        result = Statement(0, text)
    } catch (e: Token.ParseFailed) {
        println("Trying out as Expression")
        try {
            result = Expression(0, text)
        } catch (e: Token.ParseFailed) {
            throw CanNotParse()
        }
    }

    if (result.pos != text.length)
        throw CanNotParse()

    return result
}