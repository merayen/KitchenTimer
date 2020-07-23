package net.merayen.kitchentimer.utils.formula

import kotlin.math.pow

open class InterpreterError(message: String) : RuntimeException(message)
class VariableNotDefined(name: String) : InterpreterError(name)

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

    fun run(statement: Statement) {
        registry[statement.destination.name] = run(statement.expression)
    }

    fun run(expression: Expression): Double {
        val list = ArrayList(expression.list)

        if (list.size == 1)
            return getNumberFromToken(list[0])

        var numbers = list.filterIndexed { index, _ -> index % 2 == 0 }.map { getNumberFromToken(it) }
        var operators = list.filterIndexed { index, _ -> index % 2 > 0 }.map { (it as Operator).operator }

        for (precedence in OPERATOR_PRECEDENCE) { // Handle operators in correct precedence
            val temporaryNumbers = ArrayList(numbers) as ArrayList<Double?>
            val temporaryOperators = ArrayList(operators) as ArrayList<Operator.Type?>

            if (temporaryNumbers.size - 1 != temporaryOperators.size)
                throw RuntimeException("Should not happen: Nums=$numbers, Ops=$operators")

            for ((index, operator) in operators.withIndex()) {
                if (operator == precedence) {
                    val a = numbers[index]
                    val b = numbers[index + 1]

                    temporaryNumbers[index] = when (operator) {
                        Operator.Type.ADD -> a + b
                        Operator.Type.SUB -> a - b
                        Operator.Type.MUL -> a * b
                        Operator.Type.DIV -> a / b
                        Operator.Type.POW -> a.pow(b)
                    }

                    // Mark elements for deletion as we have reduced
                    temporaryNumbers[index + 1] = null
                    temporaryOperators[index] = null
                }
            }

            println(temporaryNumbers)
            numbers = temporaryNumbers.filterNotNull()
            operators = temporaryOperators.filterNotNull()

            val formulaReconstructed =
                numbers.mapIndexed { index, item -> "$item ${if (index < operators.size) operators[index].code else ""}" }
                    .joinToString(" ")
            println("Handled ${precedence}: Nums=$numbers, Ops=$operators, formula: $formulaReconstructed")
        }

        if (numbers.size != 1 || operators.isNotEmpty())
            throw RuntimeException("Should not happen: Nums=$numbers, Ops=$operators")

        return numbers[0]
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