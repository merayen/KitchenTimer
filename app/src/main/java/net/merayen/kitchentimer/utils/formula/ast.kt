package net.merayen.kitchentimer.utils.formula

import java.lang.reflect.InvocationTargetException
import kotlin.reflect.KClass


abstract class Token(val startPos: Int, private val text: String) {
    class ParseFailed internal constructor() : RuntimeException()

    var pos = startPos
        private set

    private val children = ArrayList<Token>()

    fun <T : Token> consume(cls: KClass<out T>): T? {
        val instance: T
        try {
            instance = cls.constructors.first().call(pos, text)
        } catch (exception: InvocationTargetException) {
            if (exception.targetException is ParseFailed)
                return null
            else
                throw exception
        }

        if (instance.pos <= pos)
            throw RuntimeException("${instance::class.simpleName} did not consume anything. Forgotten to call fail()?")

        pos = instance.pos
        children.add(instance)
        return instance
    }

    fun consume(searchText: String): String? {
        if (this.text.substring(pos).startsWith(searchText)) {
            pos += searchText.length
            return searchText
        }

        return null
    }

    fun consume(char: Char): Char? {
        if (text[pos] == char) {
            pos++
            return char
        }

        return null
    }

    fun consume(chars: CharArray): String? {
        var read = 0
        do {
            var hit = false
            for (c in chars) {
                if (text[pos + read] == c) {
                    read += 1
                    hit = true
                }
            }
        } while (hit)

        if (read == 0)
            return null

        val previousPos = pos
        pos += read

        return text.substring(previousPos, pos)
    }

    protected fun fail(): Nothing {
        println("Parsing stopped at: ${Thread.currentThread().stackTrace[2]}")
        throw ParseFailed()
    }
}

abstract class LiteralValue(pos: Int, text: String) : Token(pos, text)

class Whitespace(pos: Int, text: String) : Token(pos, text) {
    init {
        var read = false
        while (consume(" ") ?: consume("\n") ?: consume("\t") != null)
            read = true

        if (!read)
            fail()
    }
}

class Statement(pos: Int, text: String) : Token(pos, text) {
    var destination: Variable
        private set

    init {
        consume(Whitespace::class)
        destination = consume(Variable::class) ?: fail()
        consume(Assign::class) ?: fail()
        consume(Whitespace::class)
        consume(Expression::class) ?: fail()
    }
}

class Expression(pos: Int, text: String) : Token(pos, text) {
    lateinit var destination: Variable
        private set

    init {
        var read = false
        while (consume(Variable::class) ?: consume(Number::class) != null) {
            read = true

            consume(Whitespace::class)
            consume(Operator::class) ?: break
            consume(Whitespace::class)
        }

        if (!read)
            fail()
    }
}

class Number(pos: Int, text: String) : LiteralValue(pos, text) {
    init {
        var read = false
        while ("0123456789.".map { consume(it) }.any { it != null })
            read = true

        if (!read)
            fail()
    }
}

class Operator(pos: Int, text: String) : Token(pos, text) {
    enum class Type(val code: String) { ADD("+"), SUB("-"), MUL("*"), DIV("/") }

    init {
        Type.values().firstOrNull { consume(it.code) != null } ?: fail()
    }
}

class Assign(pos: Int, text: String) : Token(pos, text) {
    init {
        consume("=") ?: fail()
    }
}

class Variable(pos: Int, text: String) : Token(pos, text) {
    companion object {
        private val allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_ .".toCharArray()
    }

    init {
        consume("[") ?: fail()
        println("Kom hit A")
        consume(allowedCharacters) ?: fail()
        println("Kom hit B")
        consume("]") ?: fail()
        println("Kom hit C")
    }
}