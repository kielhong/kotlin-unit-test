package com.example.demo.mockk

import io.kotest.core.spec.style.FunSpec
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify

class MockkTest : FunSpec({
    val car1 = mockk<Car>()

    beforeEach {
        MockKAnnotations.init(this)
    }

    test("mockk test") {
        every { car1.drive("NORTH") } returns "OK"
        every { car1.drive(any()) } answers { "OK" }
        every { car1.drive(ofType(String::class)) } answers { "OK" }
        every { car1.brake() } just Runs
        every { car1.brake() } returns Unit
        justRun { car1.brake() }

        car1.drive("NORTH") // returns OK

        verify { car1.drive("NORTH") }

        confirmVerified(car1)

    }
})

class Car {
    fun drive(direction: String): String {
        println("drive to $direction")
        return "OK"
    }

    fun brake() {
        println("brake")
    }
}