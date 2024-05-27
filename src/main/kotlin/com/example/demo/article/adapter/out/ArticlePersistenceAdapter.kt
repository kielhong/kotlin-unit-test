package com.example.demo.article.adapter.out

import com.example.demo.article.application.port.out.DeleteArticlePort
import com.example.demo.article.application.port.out.LoadArticlePort
import com.example.demo.article.application.port.out.SaveArticlePort
import com.example.demo.article.domain.Article
import org.springframework.stereotype.Component

@Component
class ArticlePersistenceAdapter : LoadArticlePort, SaveArticlePort, DeleteArticlePort {
    override fun findArticleById(id: Long): Article? {
        TODO("Not yet implemented")
    }

    override fun findArticlesByBoardId(boardId: Long): List<Article> {
        TODO("Not yet implemented")
    }

    override fun createArticle(article: Article): Article {
        TODO("Not yet implemented")
    }

    override fun deleteArticle(id: Long) {
        TODO("Not yet implemented")
    }
}
