package net.merayen.kitchentimer.utils.formula

import kotlin.math.pow

open class InterpreterError(message: String) : RuntimeException(message)
class VariableNotDefined(name: String) : InterpreterError(name)

/**
 * Interprets a very simple and safe math language that looks like math.
 */
class Interpreter {
    companion object {
        val OPERATOR_PRECEDENCE = arrayOf(
            Operator.Type.POW,
            Operator.Type.DIV,
            Operator.Type.MUL,
            Operator.Type.SUB,
            Operator.Type.ADD
        )
    }

    val registry = HashMap<String, Double>()

    /**
     * Run a complete math formula script.
     */
    fun run(codeBlock: String) {
        for (code in codeBlock.split("\n")) {
            if (code.trim().isEmpty())
                continue

            val token = parse(code)

            if (token !is Statement)
                throw InterpreterError("Only statements can be run in a code block")

            run(token)
        }
    }

    /**
     * Runs a Statement and stores the result into the registry.
     */
    fun run(statement: Statement) {
        registry[statement.destination.name] = run(statement.expression)
    }

    /**
     * Runs an expression and returns the value it produces.
     */
    fun run(expression: Expression): Double {
        val list = ArrayList(expression.list)

        if (list.size == 1)
            return getNumberFromToken(list[0])

        var numbers = list.filterIndexed { index, _ -> index % 2 == 0 }.map { getNumberFromToken(it) }
        var operators = list.filterIndexed { index, _ -> index % 2 > 0 }.map { (it as Operator).operator }

        for (precedence in OPERATOR_PRECEDENCE) { // Handle operators in correct precedence
            val tempNumbers = ArrayList<Double>()
            val tempOperators = ArrayList<Operator.Type>()

            tempNumbers.add(numbers[0])

            for ((index, operator) in operators.withIndex()) {
                if (operator == precedence) {
                    val pos = tempNumbers.size - 1
                    when (operator) {
                        Operator.Type.ADD -> tempNumbers[pos] += numbers[index + 1]
                        Operator.Type.SUB -> tempNumbers[pos] -= numbers[index + 1]
                        Operator.Type.MUL -> tempNumbers[pos] *= numbers[index + 1]
                        Operator.Type.DIV -> tempNumbers[pos] /= numbers[index + 1]
                        Operator.Type.POW -> tempNumbers[pos] = tempNumbers[pos].pow(numbers[index + 1])
                    }
                } else {
                    tempOperators.add(operators[index])
                    tempNumbers.add(numbers[index + 1])
                }
            }

            if (tempOperators.isEmpty())
                return tempNumbers[0]

            numbers = tempNumbers
            operators = tempOperators
        }

        throw RuntimeException("Should not happen: Nums=$numbers, Ops=$operators")
    }

    private fun getNumberFromToken(token: Token): Double {
        return when (token) {
            is Number -> token.number
            is Variable -> registry[token.name] ?: throw VariableNotDefined(token.name)
            is Parenthesis -> run(token.expression)
            else -> throw InterpreterError("Expected token Variable, Number or Parentheses, but got ${token::class.simpleName}")
        }
    }
}