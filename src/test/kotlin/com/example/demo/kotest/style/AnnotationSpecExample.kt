package com.example.demo.kotest.style

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class AnnotationSpecExample : AnnotationSpec() {
    @BeforeAll
    fun beforeAll() {
        println("Before all tests")
    }

    @BeforeEach
    fun beforeTest() {
        println("Before each test")
    }

    @Test
    fun test1() {
        1 shouldBe 1
    }

    @Test
    fun test2() {
        3 shouldBe 3
    }
}