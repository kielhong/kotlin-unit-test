package com.example.demo.article.adapter.out

import article.adapter.out.persistence.jpa.ArticleJpaEntityFixtures
import article.adapter.out.persistence.jpa.BoardJpaEntityFixtures
import com.example.demo.article.adapter.out.jpa.ArticleJpaRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class ArticleJpaRepositorySpec(
    private val articleJpaRepository: ArticleJpaRepository,
    private val entityManager: TestEntityManager,
) : DescribeSpec() {
    override fun extensions() = listOf(SpringExtension)

    init {
        describe("findAllByBoardId") {
            context("boardId에 해당하는 article이 존재하면") {
                beforeEach {
                    val boardJpaEntity = entityManager.persist(BoardJpaEntityFixtures.stub(id = 0))
                    (1L..3L).map { ArticleJpaEntityFixtures.stub(id = 0, board = boardJpaEntity) }
                        .forEach { entityManager.persist(it) }
                }

                it("boardId에 해당하는 article 목록을 반환한다") {
                    val articles = articleJpaRepository.findAllByBoardId(1)
                    articles.size shouldBe 3
                }
            }
        }
    }
}
