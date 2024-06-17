package com.example.demo.article.adapter.out

import article.adapter.out.persistence.jpa.ArticleJpaEntityFixtures
import article.adapter.out.persistence.jpa.BoardJpaEntityFixtures
import article.domain.ArticleFixtures
import com.example.demo.article.adapter.out.jpa.ArticleJpaRepository
import com.example.demo.article.adapter.out.jpa.BoardJpaRepository
import io.kotest.assertions.throwables.shouldThrow
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
                val result = sut.findArticleById(1)
                result?.id shouldBe articleJpaEntity.id
            }
        }

        context("article이 존재하지 않으면") {
            every { articleJpaRepository.findByIdOrNull(any()) } returns null

            it("null을 반환한다") {
                val result = sut.findArticleById(1)
                result shouldBe null
            }
        }
    }

    describe("findArticlesByBoardId") {
        context("board에 속한 article이 존재하면") {
            every { articleJpaRepository.findAllByBoardId(any()) } returns
                (1L..3L).map { ArticleJpaEntityFixtures.stub(id = it) }
            it("article 목록을 반환한다") {
                val result = sut.findArticlesByBoardId(1)
                result.size shouldBe 3
            }
        }
    }

    describe("createArticle") {
        context("article을 생성하면") {
            val article = ArticleFixtures.article()
            val boardJpaEntity = BoardJpaEntityFixtures.stub(id = article.board.id)
            every { boardJpaRepository.findByIdOrNull(any()) } returns boardJpaEntity
            every { articleJpaRepository.save(any()) } returns
                ArticleJpaEntityFixtures.stub(
                    board = boardJpaEntity,
                    title = article.title,
                    content = article.content,
                )
            it("article이 생성된다") {
                val result = sut.createArticle(article)

                result.board.id shouldBe article.board.id
                result.title shouldBe article.title
                result.content shouldBe article.content
            }
        }

        context("board가 존재하지 않는다면") {
            val article = ArticleFixtures.article()
            every { boardJpaRepository.findByIdOrNull(any()) } returns null

            it("IllegalArgumentException을 던진다") {
                shouldThrow<IllegalArgumentException> { sut.createArticle(article) }
            }
        }
    }

    describe("updateArticle") {
        context("article이 존재하면") {
            val anotherArticle = ArticleFixtures.anotherArticle()
            val boardJpaEntity = BoardJpaEntityFixtures.stub(id = anotherArticle.board.id)
            every { articleJpaRepository.findByIdOrNull(anotherArticle.id) } returns ArticleJpaEntityFixtures.stub()
            every { boardJpaRepository.findByIdOrNull(any()) } returns boardJpaEntity
            every { articleJpaRepository.save(any()) } returns
                ArticleJpaEntityFixtures.stub(
                    board = boardJpaEntity,
                    title = anotherArticle.title,
                    content = anotherArticle.content,
                )

            it("article을 수정한다") {
                val result = sut.updateArticle(anotherArticle)

                result.id shouldBe anotherArticle.id
                result.board.id shouldBe anotherArticle.board.id
                result.title shouldBe anotherArticle.title
                result.content shouldBe anotherArticle.content
            }
        }

        context("board가 존재하지 않는다면") {
            val anotherArticle = ArticleFixtures.anotherArticle()
            every { boardJpaRepository.findByIdOrNull(any()) } returns null

            it("IllegalArgumentException을 던진다") {
                shouldThrow<IllegalArgumentException> { sut.updateArticle(anotherArticle) }
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
