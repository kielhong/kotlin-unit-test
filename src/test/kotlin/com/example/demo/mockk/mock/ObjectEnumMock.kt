package com.example.demo.mockk.mock

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkObject

class ObjectEnumMock : FunSpec({
    test("object mock") {
        mockkObject(ObjMock)
        every { ObjMock.add(1, 2) } returns 50

        ObjMock.add(1, 2) shouldBe 50
    }

    test("enum mock") {
        mockkObject(EnumMock.CONSTANT)
        every { EnumMock.CONSTANT.value } returns 100

        EnumMock.CONSTANT.value shouldBe 100
    }
})

object ObjMock {
    fun add(a: Int, b: Int) = a + b
}

enum class EnumMock(val value: Int) {
    CONSTANT(35),
}