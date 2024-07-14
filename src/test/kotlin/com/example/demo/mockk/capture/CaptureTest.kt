package com.example.demo.mockk.capture

import com.example.demo.mockk.mock.Car
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot

class CaptureTest : FunSpec({
    test("capture") {
        val car = mockk<Car>()
        val slot = slot<String>()

        every { car.drive(capture(slot)) } returns "OK"

        car.drive("NORTH")

        println(slot.isCaptured)
        slot.captured shouldBe "NORTH"
    }
})