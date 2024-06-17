package com.example.demo.article.adapter.out

import article.adapter.out.persistence.jpa.ArticleJpaEntityFixtures
import com.example.demo.article.adapter.out.jpa.ArticleJpaRepository
import com.example.demo.article.adapter.out.jpa.BoardJpaRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.springframework.data.repository.findByIdOrNull

class ArticlePersistenceAdapterSpec : DescribeSpec({
    val articleJpaRepository = mockk<ArticleJpaRepository>()
    val boardJpaRepository = mockk<BoardJpaRepository>()
    val sut = ArticlePersistenceAdapter(articleJpaRepository, boardJpaRepository)

    describe("findArticleById") {
        context("article이 존재하면") {
            val articleJpaEntity = ArticleJpaEntityFixtures.stub()
            every { articleJpaRepository.findByIdOrNull(any()) } returns articleJpaEntity

            it("article을 반환한다") {
                val article = sut.findArticleById(1)
                article?.id shouldBe articleJpaEntity.id
            }
        }

        context("article이 존재하지 않으면") {
            every { articleJpaRepository.findByIdOrNull(any()) } returns null

            it("null을 반환한다") {
                val article = sut.findArticleById(1)
                article shouldBe null
            }
        }
    }

    describe("findArticlesByBoardId") {
        context("board에 속한 article이 존재하면") {
            every { articleJpaRepository.findAllByBoardId(any()) } returns
                (1L..3L).map { ArticleJpaEntityFixtures.stub(id = it) }
            it("article 목록을 반환한다") {
                val articles = sut.findArticlesByBoardId(1)
                articles.size shouldBe 3
            }
        }
    }

    describe("deleteArticle") {
        context("article이 존재하면") {
            every { articleJpaRepository.deleteById(any()) } just Runs
            it("article을 삭제한다") {
                sut.deleteArticle(1)

                verify { articleJpaRepository.deleteById(1) }
            }
        }
    }
})
