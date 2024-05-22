package com.example.demo.article.application.service

import com.example.demo.article.adapter.`in`.api.exception.DomainNotFoundException
import com.example.demo.article.application.port.`in`.GetArticleUseCase
import com.example.demo.article.application.port.out.LoadArticlePort
import com.example.demo.article.domain.Article
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArticleService(
    private val loadArticlePort: LoadArticlePort,
) : GetArticleUseCase {
    @Transactional(readOnly = true)
    override fun getArticle(id: Long): Article {
        return loadArticlePort.findArticleById(id)
            ?: throw DomainNotFoundException("id: $id 에 해당하는 게시글이 없습니다.")
    }

    @Transactional(readOnly = true)
    override fun getArticlesByBoardId(boardId: Long): List<Article> {
        return loadArticlePort.findArticlesByBoardId(boardId)
    }
}
