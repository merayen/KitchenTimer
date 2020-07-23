package net.merayen.kitchentimer.utils.formula

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ParserKtTest {
    @Test
    fun `test a simple expression`() {
        val result = parse("[the result] = 5")
    }
}