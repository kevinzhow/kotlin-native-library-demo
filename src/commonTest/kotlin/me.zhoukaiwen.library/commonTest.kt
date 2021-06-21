package me.zhoukaiwen.library

import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class CommonTest {
    @Test
    fun testNasaAPOD() = runBlocking {
        val happyClient = NASA("JOdbdzShYX1MxEflQ0V0u9rNhBorfReMx4CGwg0k")
        val json = happyClient.getAPOD()
        assertTrue(json.length > 0)
    }
}