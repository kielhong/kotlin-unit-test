package com.example.demo.article.adapter.`in`.api
import article.domain.ArticleFixtures
import com.example.demo.article.adapter.`in`.api.exception.DomainNotFoundException
import com.example.demo.article.application.port.`in`.GetArticleUseCase
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(controllers = [ArticleController::class])
class ArticleControllerSpec : DescribeSpec() {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var getArticleUseCase: GetArticleUseCase

    init {
        extension(SpringExtension)

        describe("GET /api/articles/{id}") {
            context("article 이 존재하면") {
                val article = ArticleFixtures.article()
                every { getArticleUseCase.getArticle(any()) } returns article

                it("article을 반환") {
                    mockMvc
                        .get("/api/articles/1")
                        .andExpect {
                            status { isOk() }
                            content {
                                jsonPath("$.id") { value(1L) }
                                jsonPath("$.board.id") { value(article.board.id) }
                                jsonPath("$.board.name") { value(article.board.name) }
                                jsonPath("$.title") { value(article.title) }
                                jsonPath("$.content") { value(article.content) }
                            }
                        }
                }
            }

            context("article 이 존재하지 않으면") {
                every { getArticleUseCase.getArticle(any()) } throws DomainNotFoundException("id: 1 에 해당하는 게시글이 없습니다.")

                it("404 Not Found") {
                    mockMvc
                        .get("/api/articles/1")
                        .andExpect {
                            status { isNotFound() }
                            jsonPath("$.message") { value("id: 1 에 해당하는 게시글이 없습니다.") }
                        }
                }
            }
        }
    }
}
