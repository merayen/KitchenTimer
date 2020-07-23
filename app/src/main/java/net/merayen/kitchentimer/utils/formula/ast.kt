package net.merayen.kitchentimer.utils.formula

import kotlin.reflect.KClass

abstract class Token(private var startPos: Int, private val text: String) {
    var pos = startPos
        private set

    private val children = ArrayList<Token>()

    val isValid: Boolean
        get() = startPos != pos

    protected abstract fun onHandle(): Boolean

    fun <T : Token> consume(cls: KClass<out T>): T? {
        val instance = cls.constructors.first().call(pos, text)

        if (instance.onHandle()) {
            if (instance.pos <= pos)
                throw RuntimeException("${instance::class.simpleName} did not consume anything even though it reports it handled characters")

            pos = instance.pos
            children.add(instance)
            return instance
        }

        return null
    }

    fun consume(text: String): Boolean {
        if (text.substring(pos).startsWith(text)) {
            pos += text.length
            return true
        }

        return false
    }
}

abstract class LiteralValue(pos: Int, text: String) : Token(pos, text)

class Whitespace(pos: Int, text: String) : Token(pos, text) {
    override fun onHandle(): Boolean {
        var read = 0
        while (consume(" ") || consume("\n") || consume("\t"))
            read++

        return read > 0
    }
}

class Statement(pos: Int, text: String) : Token(pos, text) {
    lateinit var destination: Variable
        private set

    override fun onHandle(): Boolean {
        consume(Whitespace::class)
        destination = consume(Variable::class) ?: return false
        consume(Assign::class) ?: return false
        consume(Whitespace::class)
        consume(Expression::class)
    }
}

class Expression(pos: Int, text: String) : Token(pos, text) {
    lateinit var destination: Variable
        private set

    override fun onHandle(): Boolean {
        var read = false
        while (consume(Variable::class) != null || consume(Number::class) != null) {
            read = true
            if (consume(Operator::class) == null)
                break
        }

        return read
    }
}

class Number(pos: Int, text: String) : LiteralValue(pos, text) {
    override fun onHandle(): Boolean {
        TODO("Not yet implemented")
    }
}

class Operator(pos: Int, text: String) : Token(pos, text) {
    enum class Type(val code: String) {
        ADD("+"),
        SUB("-"),
        MUL("*"),
        DIV("/")
    }

    override fun onHandle(): Boolean {
        TODO("Not yet implemented")
    }
}

class Assign(pos: Int, text: String) : Token(pos, text) {
    override fun onHandle(): Boolean {
        TODO("Not yet implemented")
    }
}

class Variable(pos: Int, text: String) : Token(pos, text) {
    override fun onHandle(): Boolean {
        TODO("Not yet implemented")
    }
}

fun parse(text: String): Statement {

}