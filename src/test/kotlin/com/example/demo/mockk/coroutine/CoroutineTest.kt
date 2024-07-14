package com.example.demo.mockk.coroutine

import com.example.demo.mockk.mock.Car
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk

class CoroutineTest : FunSpec({
    test("coroutine mock") {
        val car = mockk<Car>()

        coEvery { car.drive(any()) } returns "OK"

        car.drive("NORTH") shouldBe "OK"

        coVerify { car.drive("NORTH") }

        confirmVerified(car)
    }
})
