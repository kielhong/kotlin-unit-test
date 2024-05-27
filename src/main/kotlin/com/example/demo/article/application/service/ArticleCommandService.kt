package com.example.demo.article.application.service

import com.example.demo.article.adapter.`in`.api.dto.ArticleRequest
import com.example.demo.article.application.port.`in`.CreateArticleUseCase
import com.example.demo.article.application.port.`in`.DeleteArticleUseCase
import com.example.demo.article.application.port.out.DeleteArticlePort
import com.example.demo.article.application.port.out.LoadBoardPort
import com.example.demo.article.application.port.out.SaveArticlePort
import com.example.demo.article.domain.Article
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ArticleCommandService(
    private val saveArticlePort: SaveArticlePort,
    private val deleteArticlePort: DeleteArticlePort,
    private val loadBoardPort: LoadBoardPort,
) : CreateArticleUseCase, DeleteArticleUseCase {
    override fun createArticle(request: ArticleRequest): Article {
        val board = requireNotNull(loadBoardPort.findBoardById(request.boardId)) { "존재하지 않는 boardId : ${request.boardId} 입니다." }

        return saveArticlePort.createArticle(
            Article(
                id = 0,
                title = request.title,
                content = request.content,
                board = board,
            ),
        )
    }

    override fun deleteArticle(id: Long) {
        deleteArticlePort.deleteArticle(id)
    }
}
