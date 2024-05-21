package article.domain

import com.example.demo.article.domain.Article

object ArticleFixtures {
    fun article() =
        Article(
            id = 1L,
            board = BoardFixtures.board(),
            title = "title",
            content = "content",
        )
}
