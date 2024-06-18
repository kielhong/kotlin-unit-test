package com.example.demo.article.adapter.out

import article.adapter.out.persistence.jpa.BoardJpaEntityFixtures
import com.example.demo.article.adapter.out.jpa.BoardJpaRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.repository.findByIdOrNull

class BoardPersistenceAdapterSpec : DescribeSpec({
    val boardJpaRepository = mockk<BoardJpaRepository>()
    val sut = BoardPersistenceAdapter(boardJpaRepository)

    describe("findBoardById") {
        context("id에 해당하는 BoardJpaEntity가 있따면") {
            val boardJpaEntity = BoardJpaEntityFixtures.stub()
            every { boardJpaRepository.findByIdOrNull(any()) } returns boardJpaEntity

            it("board를 반환") {
                val result = sut.findBoardById(1)
                result?.id shouldBe boardJpaEntity.id
            }
        }
    }
})
