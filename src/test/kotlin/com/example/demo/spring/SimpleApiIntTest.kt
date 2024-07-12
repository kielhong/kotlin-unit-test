package com.example.demo.spring

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.verify
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest(classes = [SimpleApi::class])
@AutoConfigureMockMvc
class SimpleApiIntTest(
    private val mockMvc: MockMvc
) : DescribeSpec() {
    override fun extensions() = listOf(SpringExtension)

    @MockkBean
    private lateinit var simpleUseCase: SimpleUseCase

    init {
        describe("GET /api/simple") {
            context("simple이 존재하면") {
                every { simpleUseCase.exist() } returns true
                every { simpleUseCase.execute() } returns "Hello, World!"

                it("'Hello, World!'를 반환") {
                    mockMvc.get("/api/simple")
                        .andExpect {
                            status { isOk() }
                            content { string("Hello, World!") }
                        }

                    verify {
                        simpleUseCase.exist()
                        simpleUseCase.execute()
                    }
                    confirmVerified(simpleUseCase)
                }
            }

            context("simple이 존재하지 않으면") {
                every { simpleUseCase.exist() } returns false
                every { simpleUseCase.execute() } returns "NOT FOUND"

                it("'NOT FOUND'를 반환") {
                    mockMvc.get("/api/simple")
                        .andExpect {
                            status { isOk() }
                            content { string("NOT FOUND") }
                        }

                    verify(exactly = 0) { simpleUseCase.execute() }
                }
            }
        }
    }
}