package com.example.demo.mockk.mock

import io.kotest.core.spec.style.FunSpec
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify

class MockkTest : FunSpec({
    test("mockk test") {
        val car1 = mockk<Car>()

        every { car1.drive("NORTH") } returns "OK"

        car1.drive("NORTH") // returns OK

        verify { car1.drive("NORTH") }

        confirmVerified(car1)

    }
})

class Car {
    fun drive(direction: String): String {
        TODO("Not Implemented")
    }
}