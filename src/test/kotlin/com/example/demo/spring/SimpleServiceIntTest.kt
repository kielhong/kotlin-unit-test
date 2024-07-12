package com.example.demo.spring

import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

//@SpringBootTest(classes = [SimpleService::class])
@ContextConfiguration(classes = [SimpleService::class])
class SimpleServiceIntTest : FunSpec() {
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    private lateinit var simpleUseCase: SimpleUseCase

    init {
        test("simple service test") {
            simpleUseCase.execute() shouldBe "Hello, World!"
        }
    }
}