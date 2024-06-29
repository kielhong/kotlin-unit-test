package com.example.demo.kotest.datadrivien

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class DataDriveTestExample : FunSpec({
    // 기본형
    context("직각삼각형") {
        withData(
            PythagoreanTriple(3, 4, 5),
            PythagoreanTriple(5, 12, 13),
            PythagoreanTriple(8, 15, 17),
        ) { (a, b, c) ->
            isPythagoreanTriple(a, b, c) shouldBe true
        }
    }

    // list 전달
    context("list 직각삼각형") {
        withData(
            listOf(
                PythagoreanTriple(3, 4, 5),
                PythagoreanTriple(5, 12, 13),
                PythagoreanTriple(8, 15, 17),
            )
        ) { (a, b, c) ->
            isPythagoreanTriple(a, b, c) shouldBe true
        }
    }

    // test name by map
    context("직각삼각형 test name 1") {
        withData(
            mapOf(
                "3, 4, 5" to PythagoreanTriple(3, 4, 5),
                "6, 8, 10" to PythagoreanTriple(6, 8, 10),
                "8, 15, 17" to PythagoreanTriple(8, 15, 17),
            )
        ) { (a, b, c) ->
            isPythagoreanTriple(a, b, c) shouldBe true
        }
    }

    // test name by nameFunction
    context("직각삼각형 test name 2") {
        withData(
            nameFn = { "${it.a}^2 + ${it.b}^2 = ${it.c}^2" },
            PythagoreanTriple(3, 4, 5),
            PythagoreanTriple(5, 12, 13),
            PythagoreanTriple(8, 15, 17),
        ) { (a, b, c) ->
            isPythagoreanTriple(a, b, c) shouldBe true
        }
    }
})

data class PythagoreanTriple(val a: Int, val b: Int, val c: Int)

fun isPythagoreanTriple(a: Int, b: Int, c: Int): Boolean = a * a + b * b == c * c
