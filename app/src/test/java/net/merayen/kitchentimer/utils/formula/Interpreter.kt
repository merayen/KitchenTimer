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
            val newNumbers = ArrayList<Double>()
            val newOperators = ArrayList<Operator.Type>()

            var index = -1
            while (index < operators.size) {
                val operator = operators[index]

                if (operator == precedence) { // Deal with the operator now and compact the formula
                    val a = if (newNumbers.isEmpty()) numbers[index] else newNumbers[newNumbers.size - 1]
                    val b = numbers[index + 1]

                    val r = when (operator) {
                        Operator.Type.ADD -> a + b
                        Operator.Type.SUB -> a - b
                        Operator.Type.MUL -> a * b
                        Operator.Type.DIV -> a / b
                        Operator.Type.POW -> a.pow(b)
                    }

                    println("Handling $a ${operator.code} $b = $r")

                    if (newNumbers.isEmpty())
                        newNumbers.add(0.0)

                    newNumbers[newNumbers.size - 1] = r

                    index++

                } else { // Not doing this operator now. Just pass number and operator for later solving
                    newNumbers.add(numbers[index])
                    newOperators.add(operator)
                }
            }

            newNumbers.add(numbers[numbers.size - 1])

            numbers = newNumbers
            operators = newOperators

            val formulaReconstructed =
                numbers.mapIndexed { index, item -> "$item ${if (index < operators.size) operators[index].code else ""}" }
                    .joinToString(" ")
            println("Handled ${precedence}: Nums=$numbers, Ops=$operators, formula: $formulaReconstructed")

            if (numbers.size - 1 != operators.size)
                throw RuntimeException("Should not happen")
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