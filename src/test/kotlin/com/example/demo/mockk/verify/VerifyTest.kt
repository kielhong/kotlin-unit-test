package com.example.demo.mockk.verify

import com.example.demo.mockk.mock.Car
import io.kotest.core.spec.style.FunSpec
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class VerifyTest : FunSpec({
    test("verify") {
        val car = mockk<Car>()

        every { car.drive(any()) } returnsMany listOf("OK", "OK2", "OK3", "OK4")
        //every { car.drive("SOUTH") } returns "OK"
        //every { car.drive("WEST") } returns "OK"

        car.drive("NORTH")
        car.drive("NORTH")
        car.drive("SOUTH")
        car.drive("NORTH")

        verify {
            car.drive("NORTH")
            car.drive("SOUTH")
        }
        //verify(exactly = 3) { car.drive("NORTH") }
        verify(atLeast = 1) { car.drive("NORTH") }
        verify(atMost = 3) { car.drive("NORTH") }
    }
})