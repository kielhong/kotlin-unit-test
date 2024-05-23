package com.example.demo.article.application.service

import article.adapter.`in`.api.dto.ArticleRequestFixtures
import article.domain.ArticleFixtures
import article.domain.BoardFixtures
import com.example.demo.article.application.port.out.LoadBoardPort
import com.example.demo.article.application.port.out.SaveArticlePort
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ArticleCommandServiceSpec : DescribeSpec({
    val saveArticlePort = mockk<SaveArticlePort>()
    val loadBoardPort = mockk<LoadBoardPort>()
    val sut = ArticleCommandService(saveArticlePort, loadBoardPort)

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
})
