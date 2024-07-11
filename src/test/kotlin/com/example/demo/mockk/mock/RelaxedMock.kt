package com.example.demo.mockk.mock

import io.kotest.core.spec.style.FunSpec
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RelaxedMock : FunSpec({
    test("relaxed mock") {
        val car = mockk<Car>(relaxed = true)

        car.drive("NORTH") // returns null

        verify { car.drive("NORTH") }
    }
})

class RelaxedMock2 {
    @MockK(relaxed = true)
    lateinit var car1: Car

    @MockK
    lateinit var car2: Car

    @RelaxedMockK
    lateinit var car3: Car

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun testCar1() {
        car1.drive("NORTH") // returns null

        verify { car1.drive("NORTH") }
    }

    @Test
    fun testCar2() {
        car2.drive("NORTH") // returns null

        verify { car2.drive("NORTH") }
    }

    @Test
    fun testCar3() {
        car3.drive("NORTH") // returns null

        verify { car3.drive("NORTH") }
    }
}