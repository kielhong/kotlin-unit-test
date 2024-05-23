package com.example.demo.article.application.port.out

import com.example.demo.article.domain.Article

interface SaveArticlePort {
    fun createArticle(article: Article): Article
}
