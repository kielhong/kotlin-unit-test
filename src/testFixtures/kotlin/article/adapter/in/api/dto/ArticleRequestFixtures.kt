package article.adapter.`in`.api.dto

import com.example.demo.article.adapter.`in`.api.dto.ArticleRequest

object ArticleRequestFixtures {
    fun stub(
        boardId: Long = 1L,
        title: String = "title",
        content: String = "content",
    ) = ArticleRequest(
        boardId = boardId,
        title = title,
        content = content,
    )
}
