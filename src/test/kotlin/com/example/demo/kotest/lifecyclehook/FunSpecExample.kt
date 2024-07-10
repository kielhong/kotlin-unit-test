package com.example.demo.kotest.lifecyclehook

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class LifeCycleExample : FunSpec({
    beforeSpec {
        println("before spec")
    }

    beforeEach {
        println("before test")
    }

    afterEach {
        println("after test")
    }

    afterSpec {
        println("after spec")
    }

    test("String length 는 문자열의 길이를 반환해야 한다") {
        "test".length shouldBe 4
        "".length shouldBe 0
    }

    context("toInt") {
        test("수자형태 문자를 수자로 변환한다") {
            "12345".toInt() shouldBe 12345
        }

        test("수자형태가 아니면 exception") {
            shouldThrow<NumberFormatException> {
                "string".toInt()
            }
        }
    }
})