package net.merayen.kitchentimer.utils.formula

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class InterpreterTest {
    @Test
    @Order(1)
    fun `operator precedence correctly set up`() {
        assertEquals(Operator.Type.values().size, Interpreter.OPERATOR_PRECEDENCE.size)
    }

    @Test
    @Order(2)
    fun `simple assignment test`() {
        val interpreter = Interpreter()
        interpreter.run(parse("[a] = 42") as Statement)

        assertEquals(42.0, interpreter.registry["a"])
    }

    @Test
    @Order(3)
    fun `check operator precedence`() {
        val testFormulas = mapOf(
            "[a] = 3 + 5" to (3.0 + 5),
            "[a] = 3 + 5 * 7" to (3.0 + 5 * 7),
            "[a] = 3 * 5 + 7 - 5 + 2 + 3" to (3.0 * 5 + 7 - 5 + 2 + 3),
            "[a] = 3 * 5 / 7 * 11" to (3 * 5 / 7.0 * 11)
        )

        val interpreter = Interpreter()

        for ((formula, expected) in testFormulas.entries) {
            val statement = parse(formula) as Statement
            println("Testing $formula")
            interpreter.run(statement)

            assertEquals(1, interpreter.registry.size)
            assertEquals((expected * 1000000).toLong() / 1000000.0, (interpreter.registry["a"]!! * 1000000).toLong() / 1000000.0, "Formula: $formula")
        }
    }
}