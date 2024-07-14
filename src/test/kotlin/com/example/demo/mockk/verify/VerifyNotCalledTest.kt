package com.example.demo.mockk.verify

import com.example.demo.mockk.mock.Car
import io.kotest.core.spec.style.FunSpec
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class VerifyNotCalledTest : FunSpec({
    test("verify not called") {
        val car = mockk<Car>()
        val car2 = mockk<Car>()

        every { car.drive("NORTH") } returns "OK"
        every { car.drive("SOUTH") } returns "OK"
        every { car.drive("WEST") } returns "OK"
        every { car2.drive("EAST") } returns "OK"

        car.drive("NORTH")
        car.drive("SOUTH")

        verify(exactly = 0) { car.drive("WEST") }
        verify { car2 wasNot Called }
    }
})