package net.merayen.kitchentimer.utils.formula

import java.lang.reflect.InvocationTargetException
import kotlin.reflect.KClass


abstract class Token(val startPos: Int, private val text: String) {
    class ParseFailed internal constructor() : RuntimeException()

    var pos = startPos
        private set

    private val children = ArrayList<Token>()

    abstract fun dump(): String

    protected fun consumeAny(vararg anyClasses: KClass<out Token>): Token? {
        var instance: Token? = null
        for (cls in anyClasses) {
            try {
                instance = cls.constructors.first().call(pos, text)
                break
            } catch (exception: InvocationTargetException) {
                if (exception.targetException !is ParseFailed)
                    throw exception
            }
        }

        instance ?: return null

        if (instance.pos <= pos)
            throw RuntimeException("${instance::class.simpleName} did not consume anything. Forgotten to call fail()?")

        pos = instance.pos
        children.add(instance)

        return instance
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T : Token>consume(cls: KClass<out T>): T? = consumeAny(cls) as T?

    protected fun consumeAny(vararg searchText: String): String? {
        for (s in searchText) {
            if (pos < text.length && text.startsWith(s, pos)) {
                pos += s.length
                return s
            }
        }

        return null
    }

    protected fun consume(searchText: String): String? = consumeAny(searchText)

    protected fun consume(char: Char): Char? {
        if (pos < text.length && text[pos] == char) {
            pos++
            return char
        }

        return null
    }

    protected fun consume(chars: CharArray): String? {
        var read = 0
        do {
            var hit = false
            for (c in chars) {
                if ((pos + read) < text.length && text[pos + read] == c) {
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

    protected fun fail(): Nothing = throw ParseFailed()
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

    override fun dump() = " "
}

class Statement(pos: Int, text: String) : Token(pos, text) {
    var destination: Variable
        private set

    var expression: Expression
        private set

    init {
        consume(Whitespace::class)
        destination = consume(Variable::class) ?: fail()
        consume(Whitespace::class)
        consume(Assign::class) ?: fail()
        consume(Whitespace::class)
        expression = consume(Expression::class) ?: fail()
    }

    override fun dump(): String {
        return "${destination.dump()} = ${expression.dump()};"
    }
}

class Expression(pos: Int, text: String) : Token(pos, text) {
    val list = ArrayList<Token>()

    init {
        while (true) {
            // TODO look for parentheses?
            list.add(consumeAny(Variable::class, Number::class, Parenthesis::class) ?: break)
            consume(Whitespace::class)
            list.add(consume(Operator::class) ?: break)
            consume(Whitespace::class)
        }

        if (list.isEmpty())
            fail()
    }

    override fun dump() = list.joinToString("") { it.dump() }
}

class Number(pos: Int, text: String) : LiteralValue(pos, text) {
    val number: Double

    init {
        val stringBuilder = StringBuilder()
        while (true) {
            val chars = "0123456789.".map { consume(it) }.filterNotNull().joinToString("")
            if (chars.isEmpty())
                break

            stringBuilder.append(chars)
       }

        if (stringBuilder.count { it == '.' } > 1)
            fail()

        if (stringBuilder.isEmpty())
            fail()

        number = stringBuilder.toString().toDouble()
    }

    override fun dump() = if (number - number.toLong() != 0.0) number.toString() else number.toLong().toString()
}

class Operator(pos: Int, text: String) : Token(pos, text) {
    enum class Type(val code: String) { ADD("+"), SUB("-"), MUL("*"), DIV("/") }

    val operator: Type

    init {
        operator = Type.values().firstOrNull { consume(it.code) != null } ?: fail()
    }

    override fun dump() = " ${operator.code} "
}

class Assign(pos: Int, text: String) : Token(pos, text) {
    init {
        consume("=") ?: fail()
    }

    override fun dump() = " = "
}

class Variable(pos: Int, text: String) : Token(pos, text) {
    companion object {
        private val allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_ .".map { it.toString() }.toTypedArray()
    }

    val name: String

    init {
        consume("[") ?: fail()

        val stringBuilder = StringBuilder()
        while (true) {
            stringBuilder.append(consumeAny(*allowedCharacters) ?: break)
        }

        if (stringBuilder.isEmpty())
            fail()

        consume("]") ?: fail()

        name = stringBuilder.toString()
    }

    override fun dump() = "[$name]"
}

class Parenthesis(pos: Int, text: String) : Token(pos, text) {
    val expression: Expression

    init {
        consume("(") ?: fail()
        consume(Whitespace::class)
        expression = consume(Expression::class) ?: fail()
        consume(Whitespace::class)
        consume(")") ?: fail()
    }

    override fun dump() = "(${expression.dump()})"
}