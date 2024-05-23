package com.example.demo.article.application.port.`in`

import com.example.demo.article.adapter.`in`.api.dto.ArticleRequest
import com.example.demo.article.domain.Article

interface CreateArticleUseCase {
    fun createArticle(request: ArticleRequest): Article
}
