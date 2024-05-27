package article.domain

import com.example.demo.article.domain.Article

object ArticleFixtures {
    fun article(id: Long = 1L) =
        Article(
            id = id,
            board = BoardFixtures.board(),
            title = "title",
            content = "content",
        )

    fun anotherArticle(id: Long = 1L) =
        Article(
            id = id,
            board = BoardFixtures.anotherBoard(),
            title = "another title",
            content = "another content",
        )
}
