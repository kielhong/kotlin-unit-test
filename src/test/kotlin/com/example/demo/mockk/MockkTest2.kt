package com.example.demo.mockk

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MockkTest2 {
    @MockK
    lateinit var car2: Car

    @Test
    fun test() {
        every { car2.drive("NORTH") } returns "OK"

        car2.drive("NORTH") // returns OK

        verify { car2.drive("NORTH") }

        confirmVerified(car2)
    }
}