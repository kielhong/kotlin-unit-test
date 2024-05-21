package com.example.demo.article.application.port.`in`

import com.example.demo.article.domain.Article

interface GetArticleUseCase {
    fun getArticle(id: Long): Article?
}
