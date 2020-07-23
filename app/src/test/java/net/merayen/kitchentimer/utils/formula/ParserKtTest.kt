package net.merayen.kitchentimer.utils.formula

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ParserKtTest {
    @Test
    fun `test a simple expression`() {
        val result = parse("[the result] = 5")

        assertTrue(result is Statement)
        result as Statement

        assertEquals("the result", result.destination.name)

        val number = result.expression.list[0]
        assertTrue(number is Number)
        number as Number
        assertEquals(5.0, number.number)
    }

    @Test
    fun `multiplying variable with a number`() {
        val statement = parse("[the result] = [age] * 42") as Statement

        assertEquals(3, statement.expression.list.size)

        val variable = statement.expression.list[0]
        val multiplier = statement.expression.list[1]
        val number = statement.expression.list[2]

        assertTrue(variable is Variable)
        assertTrue(multiplier is Operator)
        assertTrue(number is Number)

        variable as Variable
        multiplier as Operator
        number as Number

        assertEquals("the result", variable.name)
        assertEquals(Operator.Type.MUL, multiplier.operator)
        assertEquals(42.0, number.number)
    }
}