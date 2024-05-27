package com.example.demo.article.application.port.`in`

import com.example.demo.article.adapter.`in`.api.dto.ArticleRequest
import com.example.demo.article.domain.Article

interface UpdateArticleUseCase {
    fun updateArticle(
        id: Long,
        request: ArticleRequest,
    ): Article
}
