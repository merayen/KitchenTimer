package net.merayen.kitchentimer.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DurationKtTest {
	@Test
	fun check() {
		assertEquals("23h 59m 59s", durationToString(24 * 60 * 60 - 1))
		assertEquals("59m 59s", durationToString(60 * 60 - 1))
		assertEquals("59s", durationToString(59))
		assertEquals("-1s", durationToString(-1))
		assertEquals("-59m -59s", durationToString(-60 * 60 + 1))
		assertEquals("-23h -59m -59s", durationToString(-24 * 60 * 60 + 1))
	}
}