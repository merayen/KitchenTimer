package net.merayen.kitchentimer.utils.formula

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class ParserKtTest {
    @Test
    @Order(1)
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
    @Order(2)
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

        assertEquals("age", variable.name)
        assertEquals(Operator.Type.MUL, multiplier.operator)
        assertEquals(42.0, number.number)
    }

    @Test
    @Order(3)
    fun `parenthesis in expression`() {
        val statement = parse("[the result] = 1 + (2-[something]) * 4") as Statement

        assertEquals(5, statement.expression.list.size)

        assertTrue(statement.expression.list[2] is Parenthesis)

        val parentheses = statement.expression.list[2] as Parenthesis

        assertTrue(parentheses.expression.list[0] is Number)
        assertTrue(parentheses.expression.list[1] is Operator)
        assertTrue(parentheses.expression.list[2] is Variable)

        assertEquals(Operator.Type.MUL, (statement.expression.list[3] as Operator).operator)
    }

    @Test
    @Order(4)
    fun `parse expression`() {
        val expression = parse("1 + [something]")

        assertTrue(expression is Expression)
        expression as Expression

        assertTrue(expression.list[0] is Number)
        assertTrue(expression.list[1] is Operator)
        assertTrue(expression.list[2] is Variable)
    }

    @Test
    @Order(5)
    fun `parse and dump`() {
        val text = "[the result] = 1 + 2 * (3 / 4)"
        val statement = parse(text)

        assertEquals(text, statement.dump())
    }
}