package net.merayen.kitchentimer.utils.formula

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class InterpreterTest {
    @Test
    fun `check if answer is 42`() {
        val statement = parse("[the result] = 42") as Statement
        val interpreter = Interpreter()
        interpreter.run(statement)

        assertEquals(1, interpreter.registry.size)
        assertEquals(42.0, interpreter.registry["the result"])
    }
}