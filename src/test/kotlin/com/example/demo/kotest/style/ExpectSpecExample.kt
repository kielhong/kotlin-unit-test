package com.example.demo.kotest.style

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

class ExpectSpecExample : ExpectSpec() {
    init {
        context("String length") {
            expect("should return the length of the string") {
                "test".length shouldBe 4
                "".length shouldBe 0
            }
        }

        context("toInt") {
            expect("should convert a numeric string to a number") {
                "12345".toInt() shouldBe 12345
            }

            expect("should throw an exception if it is not a numeric string") {
                shouldThrow<NumberFormatException> {
                    "string".toInt()
                }
            }
        }
    }
}