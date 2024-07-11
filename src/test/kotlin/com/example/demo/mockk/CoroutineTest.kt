package com.example.demo.mockk

import io.kotest.core.spec.style.FunSpec
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class CoroutineTest : FunSpec({
    test("coroutine mock") {
        val car = mockk<CoCar>()

        coEvery { car.drive("NORTH") } returns "OK"

        car.drive("NORTH")

        coVerify { car.drive("NORTH") }
    }
})

class CoCar {
    suspend fun drive(direction: String): String {
        TODO()
    }
}