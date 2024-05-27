package com.example.demo.article.application.service

import com.example.demo.article.adapter.`in`.api.dto.ArticleRequest
import com.example.demo.article.adapter.`in`.api.exception.DomainNotFoundException
import com.example.demo.article.application.port.`in`.CreateArticleUseCase
import com.example.demo.article.application.port.`in`.DeleteArticleUseCase
import com.example.demo.article.application.port.`in`.UpdateArticleUseCase
import com.example.demo.article.application.port.out.DeleteArticlePort
import com.example.demo.article.application.port.out.LoadArticlePort
import com.example.demo.article.application.port.out.LoadBoardPort
import com.example.demo.article.application.port.out.SaveArticlePort
import com.example.demo.article.domain.Article
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ArticleCommandService(
    private val loadArticlePort: LoadArticlePort,
    private val saveArticlePort: SaveArticlePort,
    private val deleteArticlePort: DeleteArticlePort,
    private val loadBoardPort: LoadBoardPort,
) : CreateArticleUseCase, UpdateArticleUseCase, DeleteArticleUseCase {
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

    override fun updateArticle(
        id: Long,
        request: ArticleRequest,
    ): Article {
        val board = requireNotNull(loadBoardPort.findBoardById(request.boardId)) { "존재하지 않는 boardId : ${request.boardId} 입니다." }
        val article = checkNotNull(loadArticlePort.findArticleById(id)) { throw DomainNotFoundException("id : $id 에 해당하는 게시글이 없습니다.") }

        article.update(
            title = request.title,
            content = request.content,
            board = board,
        )

        return saveArticlePort.updateArticle(article)
    }

    override fun deleteArticle(id: Long) {
        deleteArticlePort.deleteArticle(id)
    }
}
