package com.example.demo.mockk.verify

import com.example.demo.mockk.mock.Car
import io.kotest.core.spec.style.FunSpec
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ConfirmedVerifyTest : FunSpec({
    test("confirmed verify") {
        val car = mockk<Car>()

        every { car.drive("NORTH") } returns "OK"

        car.drive("NORTH")

        //verify { car.drive("NORTH") }

        confirmVerified(car)
    }
})