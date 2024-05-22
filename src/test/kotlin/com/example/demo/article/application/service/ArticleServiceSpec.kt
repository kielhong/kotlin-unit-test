package com.example.demo.article.application.service

import article.domain.ArticleFixtures
import com.example.demo.article.adapter.`in`.api.exception.DomainNotFoundException
import com.example.demo.article.application.port.out.LoadArticlePort
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ArticleServiceSpec : DescribeSpec({
    val loadArticlePort = mockk<LoadArticlePort>()
    val sut = ArticleService(loadArticlePort)

    describe("getArticle") {
        context("article이 존재하면") {
            every { loadArticlePort.findArticleById(1L) } returns ArticleFixtures.article(1L)

            it("article을 반환") {
                val result = sut.getArticle(1L)
                result.id shouldBe 1L
            }
        }

        context("article이 존재하지 않으면") {
            every { loadArticlePort.findArticleById(1L) } returns null

            it("DomainNotFoundException을 throw") {
                shouldThrow<DomainNotFoundException> { sut.getArticle(1L) }
                    .message shouldBe "id: 1 에 해당하는 게시글이 없습니다."
            }
        }
    }

    describe("getArticlesByBoardId") {
        context("boardId에 해당하는 article이 존재하면") {
            every { loadArticlePort.findArticlesByBoardId(1L) } returns
                (1L..3L).map { ArticleFixtures.article(it) }

            it("article 목록을 반환") {
                val result = sut.getArticlesByBoardId(1L)
                result.size shouldBe 3
            }
        }
    }
})
