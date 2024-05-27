package com.example.demo.article.application.service

import article.adapter.`in`.api.dto.ArticleRequestFixtures
import article.domain.ArticleFixtures
import article.domain.BoardFixtures
import com.example.demo.article.adapter.`in`.api.exception.DomainNotFoundException
import com.example.demo.article.application.port.out.DeleteArticlePort
import com.example.demo.article.application.port.out.LoadArticlePort
import com.example.demo.article.application.port.out.LoadBoardPort
import com.example.demo.article.application.port.out.SaveArticlePort
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify

class ArticleCommandServiceSpec : DescribeSpec({
    val loadArticlePort = mockk<LoadArticlePort>()
    val saveArticlePort = mockk<SaveArticlePort>()
    val deleteArticlePort = mockk<DeleteArticlePort>()
    val loadBoardPort = mockk<LoadBoardPort>()
    val sut = ArticleCommandService(loadArticlePort, saveArticlePort, deleteArticlePort, loadBoardPort)

    describe("createArticle") {
        context("article을 생성") {
            val request = ArticleRequestFixtures.stub()
            val article = ArticleFixtures.article()
            val board = BoardFixtures.board()
            every { loadBoardPort.findBoardById(any()) } returns board
            every { saveArticlePort.createArticle(any()) } returns article

            it("생성된 article을 반환") {
                val result = sut.createArticle(request)
                result.id shouldBe article.id

                verify {
                    saveArticlePort.createArticle(
                        withArg {
                            it.title shouldBe request.title
                            it.content shouldBe request.content
                            it.board shouldBe board
                        },
                    )
                }
            }
        }

        context("존재하지 않는 boardId 이면") {
            every { loadBoardPort.findBoardById(any()) } returns null

            it("IllegalArgumentException을 반환") {
                shouldThrow<IllegalArgumentException> {
                    sut.createArticle(ArticleRequestFixtures.stub())
                }
                    .message shouldBe "존재하지 않는 boardId : ${ArticleRequestFixtures.stub().boardId} 입니다."
            }
        }
    }

    describe("updateArticle") {
        val request = ArticleRequestFixtures.stub(2L, "another title", "another content")
        every { loadBoardPort.findBoardById(any()) } returns BoardFixtures.anotherBoard()

        context("article을 수정하면") {
            val updatedArticle = ArticleFixtures.anotherArticle()
            every { loadArticlePort.findArticleById(any()) } returns ArticleFixtures.article()
            every { saveArticlePort.updateArticle(any()) } returns updatedArticle

            it("수정된 article을 반환") {
                val result = sut.updateArticle(1L, request)

                result.id shouldBe 1L
                result.board.id shouldBe updatedArticle.board.id
                result.title shouldBe updatedArticle.title
                result.content shouldBe updatedArticle.content

                verify {
                    saveArticlePort.updateArticle(
                        withArg {
                            it.id shouldBe 1L
                            it.title shouldBe request.title
                            it.content shouldBe request.content
                            it.board.id shouldBe request.boardId
                        },
                    )
                }
            }
        }

        context("존재하지 않는 article을 수정하면") {
            every { loadArticlePort.findArticleById(any()) } returns null

            it("DomainNotFoundException 예외 던짐") {
                shouldThrow<DomainNotFoundException> {
                    sut.updateArticle(1L, request)
                }
                    .message shouldBe "id : 1 에 해당하는 게시글이 없습니다."
            }
        }

        context("변경 하려는 boardId 가 존재하지 않는 board 이면") {
            every { loadBoardPort.findBoardById(any()) } returns null

            it("IllegalArgumentException을 반환") {
                shouldThrow<IllegalArgumentException> {
                    sut.updateArticle(1L, request)
                }
                    .message shouldBe "존재하지 않는 boardId : ${request.boardId} 입니다."
            }
        }
    }

    describe("deleteArticle") {

        context("article을 삭제") {
            every { deleteArticlePort.deleteArticle(any()) } just Runs

            sut.deleteArticle(1L)

            verify { deleteArticlePort.deleteArticle(1L) }
        }
    }
})
