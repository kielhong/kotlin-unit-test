package com.example.demo.article.domain

import article.domain.ArticleFixtures
import article.domain.BoardFixtures
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ArticleSpec : DescribeSpec({
    describe("update") {
        context("article 내용을 변경하면") {
            val article = ArticleFixtures.article()

            it("변경된 내용을 확인") {
                val board = BoardFixtures.anotherBoard()
                val title = "another title"
                val content = "another content"

                article.update(board, title, content)

                article.board shouldBe board
                article.title shouldBe title
                article.content shouldBe content
            }
        }
    }
})
