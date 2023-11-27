package com.github.gunin_igor75.crypto_app

import androidx.core.util.Consumer
import org.junit.Test

import org.junit.Assert.*
import java.lang.IllegalStateException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

    }

    @Test(expected = ArithmeticException::class)
    fun divideForZero() {
        val result = 10 / 0
    }

    @Test
    fun `деление на 0`() {
        assertThrows(ArithmeticException::class.java) {
            val blackHole = 1 / 0
        }
    }
}