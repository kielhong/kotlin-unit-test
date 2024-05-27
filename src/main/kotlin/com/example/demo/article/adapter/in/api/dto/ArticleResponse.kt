package com.example.demo.article.adapter.`in`.api.dto

import com.example.demo.article.domain.Article

data class ArticleResponse(
    val id: Long,
    val board: BoardResponse,
    val title: String,
    val content: String,
) {
    companion object {
        fun from(article: Article) =
            ArticleResponse(
                id = article.id,
                board = BoardResponse.from(article.board),
                title = article.title,
                content = article.content,
            )
    }
}
