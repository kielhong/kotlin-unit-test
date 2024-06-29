package com.example.demo.kotest.style

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DescribeSpecExample : DescribeSpec({
    describe("String length") {
        it("should return the length of the string") {
            "test".length shouldBe 4
            "".length shouldBe 0
        }
    }

    describe("toInt") {
        context("numeric string") {
            it("should convert a numeric string to a number") {
                "12345".toInt() shouldBe 12345
            }
        }

        context("non-numeric string") {
            it("should throw an exception") {
                shouldThrow<NumberFormatException> {
                    "string".toInt()
                }
            }
        }
    }
})