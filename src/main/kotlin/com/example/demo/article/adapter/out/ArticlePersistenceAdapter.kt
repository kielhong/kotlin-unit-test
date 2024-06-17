package com.example.demo.article.adapter.out

import com.example.demo.article.adapter.out.jpa.ArticleJpaEntity
import com.example.demo.article.adapter.out.jpa.ArticleJpaRepository
import com.example.demo.article.adapter.out.jpa.BoardJpaRepository
import com.example.demo.article.application.port.out.DeleteArticlePort
import com.example.demo.article.application.port.out.LoadArticlePort
import com.example.demo.article.application.port.out.SaveArticlePort
import com.example.demo.article.domain.Article
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ArticlePersistenceAdapter(
    private val articleJpaRepository: ArticleJpaRepository,
    private val boardJpaRepository: BoardJpaRepository,
) : LoadArticlePort, SaveArticlePort, DeleteArticlePort {
    override fun findArticleById(id: Long): Article? =
        articleJpaRepository.findByIdOrNull(id)
            ?.toDomain()

    override fun findArticlesByBoardId(boardId: Long): List<Article> =
        articleJpaRepository.findAllByBoardId(boardId)
            .map { it.toDomain() }

    override fun createArticle(article: Article): Article {
        val boardJpaEntity =
            boardJpaRepository.findByIdOrNull(article.board.id)
                ?: throw IllegalArgumentException("Board not found")
        val articleJpaEntity = ArticleJpaEntity.from(article, boardJpaEntity)

        return articleJpaRepository.save(articleJpaEntity)
            .toDomain()
    }

    override fun updateArticle(article: Article): Article {
        val boardJpaEntity =
            boardJpaRepository.findByIdOrNull(article.board.id)
                ?: throw IllegalArgumentException("Board not found")
        val articleJpaEntity = ArticleJpaEntity.from(article, boardJpaEntity)

        return articleJpaRepository.save(articleJpaEntity)
            .toDomain()
    }

    override fun deleteArticle(id: Long) {
        articleJpaRepository.deleteById(id)
    }
}
