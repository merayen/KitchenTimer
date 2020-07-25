package net.merayen.kitchentimer.utils.formula

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import kotlin.math.pow

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
            "[a] = 1 + 2" to (1.0 + 2),
            "[a] = 1 + 2 + 3" to (1.0 + 2 + 3),
            "[a] = 3 + 5 * 7" to (3.0 + 5 * 7),
            "[a] = 3 * 5 + 7 - 5 + 2 + 3" to (3.0 * 5 + 7 - 5 + 2 + 3),
            "[a] = 3 * 5 / 7^2 * 11" to (3 * 5 / 7.0.pow(2) * 11)
        )

        val interpreter = Interpreter()

        for ((formula, expected) in testFormulas.entries) {
            val statement = parse(formula) as Statement
            interpreter.run(statement)

            assertEquals(1, interpreter.registry.size)
            assertEquals((expected * 1000000).toLong() / 1000000.0, (interpreter.registry["a"]!! * 1000000).toLong() / 1000000.0, "Formula: $formula")
        }
    }

    @Test
    @Order(4)
    fun parenthesis() {
        val interpreter = Interpreter()
        interpreter.run("[a] = 4 * (7 + 3) / 2")

        assertEquals(4 * (7 + 3) / 2.0, interpreter.registry["a"])
    }

    @Test
    @Order(5)
    fun `evaluate code block using variables`() {
        val interpreter = Interpreter()
        interpreter.run("""
            [a] = 2
            [b] = 1 + [a]
            [c] = [b]^2
            [d] = 3 * (2 + [b]^(1 / [a])) - 4 / 2
        """.trimIndent())

        assertEquals(2.0, interpreter.registry["a"])
        assertEquals(3.0, interpreter.registry["b"])
        assertEquals(9.0, interpreter.registry["c"])
        assertEquals(3  * (2 + 3.0.pow(1 / 2.0)) - 4 / 2.0, interpreter.registry["d"])
    }
}