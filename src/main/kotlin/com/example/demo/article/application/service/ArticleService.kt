package com.example.demo.article.application.service

import com.example.demo.article.application.port.`in`.GetArticleUseCase
import com.example.demo.article.domain.Article
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArticleService : GetArticleUseCase {
    @Transactional(readOnly = true)
    override fun getArticle(id: Long): Article? {
        TODO("Not yet implemented")
    }
}
