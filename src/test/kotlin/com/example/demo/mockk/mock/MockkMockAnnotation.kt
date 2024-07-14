package com.example.demo.mockk.mock

import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock

//@ExtendWith(MockKExtension::class)
class MockkMockAnnotation {
    @MockK
    lateinit var car2: Car

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun test() {
        every { car2.drive("NORTH") } returns "OK"

        car2.drive("NORTH")

        verify { car2.drive("NORTH") }

        confirmVerified(car2)
    }
}