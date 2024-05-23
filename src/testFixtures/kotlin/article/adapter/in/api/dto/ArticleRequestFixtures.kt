package article.adapter.`in`.api.dto

import com.example.demo.article.adapter.`in`.api.dto.ArticleRequest

object ArticleRequestFixtures {
    fun stub() =
        ArticleRequest(
            boardId = 1L,
            title = "title",
            content = "content",
        )
}
