package com.example.demo.article.adapter.`in`.api
import article.domain.ArticleFixtures
import com.example.demo.article.adapter.`in`.api.exception.DomainNotFoundException
import com.example.demo.article.application.port.`in`.CreateArticleUseCase
import com.example.demo.article.application.port.`in`.DeleteArticleUseCase
import com.example.demo.article.application.port.`in`.GetArticleUseCase
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

@WebMvcTest(controllers = [ArticleController::class])
class ArticleControllerSpec : DescribeSpec() {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var getArticleUseCase: GetArticleUseCase

    @MockkBean
    private lateinit var createArticleUseCase: CreateArticleUseCase

    @MockkBean
    private lateinit var deleteArticleUseCase: DeleteArticleUseCase

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

        describe("GET /api/articles?boardId={boardId}") {
            context("article 들이 존재하면") {
                it("article list 반환") {
                    val articles = (1L..3L).map { ArticleFixtures.article(it) }
                    every { getArticleUseCase.getArticlesByBoardId(any()) } returns articles

                    mockMvc
                        .get("/api/articles?boardId=1")
                        .andExpect {
                            status { isOk() }
                            content {
                                jsonPath("$.size()") { value(3) }
                                jsonPath("$[0].id") { value(1L) }
                                jsonPath("$[0].board.id") { value(articles[0].board.id) }
                                jsonPath("$[0].board.name") { value(articles[0].board.name) }
                                jsonPath("$[0].title") { value(articles[0].title) }
                                jsonPath("$[0].content") { value(articles[0].content) }
                            }
                        }
                }
            }

            context("article 들이 존재하지 않으면") {
                every { getArticleUseCase.getArticlesByBoardId(any()) } returns emptyList()

                it("empty list 반환") {
                    mockMvc
                        .get("/api/articles?boardId=1")
                        .andExpect {
                            status { isOk() }
                            content {
                                jsonPath("$.size()") { value(0) }
                            }
                        }
                }
            }
        }

        describe("POST /api/articles") {
            context("article 생성 요청이 들어오면") {
                every { createArticleUseCase.createArticle(any()) } returns ArticleFixtures.article()

                it("article 생성하고 id를 반환") {
                    mockMvc
                        .post("/api/articles") {
                            contentType = MediaType.APPLICATION_JSON
                            content =
                                """
                                {
                                    "boardId": 1,
                                    "title": "title",
                                    "content": "content"
                                }
                                """.trimIndent()
                        }
                        .andExpect {
                            status { isOk() }
                            jsonPath("$.id") { value(1L) }
                        }

                    verify { createArticleUseCase.createArticle(any()) }
                }
            }

            context("입력 정보가 비정상이면") {
                every { createArticleUseCase.createArticle(any()) } throws IllegalArgumentException("invalid request")

                it("400 Bad Request") {
                    mockMvc
                        .post("/api/articles") {
                            contentType = MediaType.APPLICATION_JSON
                            content =
                                """
                                {
                                    "boardId": 1,
                                    "title": "",
                                    "content": ""
                                }
                                """.trimIndent()
                        }
                        .andExpect {
                            status { isBadRequest() }
                        }
                }
            }
        }

        describe("PUT /api/articles/{id}") {
            context("article을 수정하면") {

                it("article 수정하고 200 OK") {
                    mockMvc
                        .put("/api/articles/1")
                        .andExpect {
                            status { isOk() }
                        }
                }
            }
        }

        describe("DELETE /api/articles/{id}") {
            context("article을 삭제하면") {
                every { deleteArticleUseCase.deleteArticle(any()) } just Runs
                // every { deleteArticleUseCase.deleteArticle(any()) } returns Unit
                // justRun { deleteArticleUseCase.deleteArticle(any()) }

                it("article 삭제하고 200 OK") {
                    mockMvc
                        .delete("/api/articles/1")
                        .andExpect {
                            status { isOk() }
                        }

                    verify { deleteArticleUseCase.deleteArticle(1L) }
                }
            }
        }
    }
}
