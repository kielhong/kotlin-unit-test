package com.example.demo.mockk.stub

import com.example.demo.mockk.mock.Car
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.justRun
import io.mockk.mockk

class StubTest : FunSpec({
    test("stub") {
        val car = mockk<Car>()

        every { car.drive("NORTH") } returns "OK"

        car.drive("NORTH") shouldBe "OK"
    }

    test("stub arg matching") {
        val car = mockk<Car>()

        every { car.drive(any()) } returns "OK"

        car.drive("NORTH") shouldBe "OK"
    }

    test("returning Unit") {
        val mockClass = mockk<MockClass>()

        every { mockClass.sum(any(), any()) } returns Unit
        every { mockClass.sum(any(), any()) } just Runs
        justRun { mockClass.sum(any(), any()) }
    }
})

class MockClass {
    fun sum(a: Int, b: Int): Unit {
        println(a + b)
    }
}