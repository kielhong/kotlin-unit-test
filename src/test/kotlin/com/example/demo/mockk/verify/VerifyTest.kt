package com.example.demo.mockk.verify

import com.example.demo.mockk.mock.Car
import io.kotest.core.spec.style.FunSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class VerifyTest : FunSpec({
    test("verify") {
        val car = mockk<Car>()

        every { car.drive("NORTH") } returns "OK"
        every { car.drive("SOUTH") } returns "OK"

        car.drive("NORTH")
        car.drive("SOUTH")

        verify {
            car.drive("NORTH")
            car.drive("SOUTH")
        }
        verify(exactly = 1) { car.drive("NORTH") }
        verify(atLeast = 1) { car.drive("NORTH") }
        verify(atMost = 1) { car.drive("NORTH") }
        verify(exactly = 0) { car.drive("WEST") }
    }
})