package com.example.demo.mockk.mock

import io.kotest.core.spec.style.FunSpec
import io.mockk.spyk
import io.mockk.verify

class Spyk : FunSpec({
    test("spyk test") {
        val car = spyk<Car>()

        car.drive("NORTH")

        verify { car.drive("NORTH") }
    }
})