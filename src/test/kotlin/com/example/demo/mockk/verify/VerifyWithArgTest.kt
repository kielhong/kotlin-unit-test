package com.example.demo.mockk.verify

import com.example.demo.mockk.mock.Car
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class VerifyWithArgTest : FunSpec({
    test("verify") {
        val car = mockk<Car>()

        every { car.drive("NORTH") } returns "OK"

        car.drive("NORTH")

        verify {
            car.drive(
                withArg { it shouldBe "NORTH" }
            )
        }
    }
})