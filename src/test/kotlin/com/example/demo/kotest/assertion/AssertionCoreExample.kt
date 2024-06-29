package com.example.demo.kotest.assertion

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.throwables.shouldThrowMessage
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeLowerCase
import io.kotest.matchers.string.shouldHaveLength
import io.kotest.matchers.string.shouldNotBeEmpty

class AssertionCoreExample : FunSpec({
    test("String length") {
        "test".length shouldBe 4

        "test".length.shouldBe(4)

        "test"
            .shouldHaveLength(4)
            .shouldBeLowerCase()
            .shouldNotBeEmpty()
            .shouldNotBeNull()
    }

    test("exception") {
        val exception = shouldThrow<NumberFormatException> {
            "string".toInt()
        }
        exception.message shouldBe "For input string: \"string\""

        shouldThrow<NumberFormatException> {
            "test".toInt()
        }.message shouldBe "For input string: \"test\""

        shouldThrowMessage("For input string: \"test\"") {
            "test".toInt()
        }
    }
})