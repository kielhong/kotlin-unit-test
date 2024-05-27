package com.example.demo.article.adapter.`in`.api

import com.example.demo.article.adapter.`in`.api.dto.ArticleRequest
import com.example.demo.article.adapter.`in`.api.dto.ArticleResponse
import com.example.demo.article.adapter.`in`.api.dto.CommandResponse
import com.example.demo.article.application.port.`in`.CreateArticleUseCase
import com.example.demo.article.application.port.`in`.DeleteArticleUseCase
import com.example.demo.article.application.port.`in`.GetArticleUseCase
import com.example.demo.article.application.port.`in`.UpdateArticleUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/articles")
class ArticleController(
    private val getArticleUseCase: GetArticleUseCase,
    private val createArticleUseCase: CreateArticleUseCase,
    private val updateArticleUseCase: UpdateArticleUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
) {
    @GetMapping("{id}")
    fun getArticle(
        @PathVariable id: Long,
    ): ArticleResponse {
        return getArticleUseCase.getArticle(id)
            .let { ArticleResponse.from(it) }
    }

    @GetMapping(params = ["boardId"])
    fun getArticlesByBoardId(
        @RequestParam boardId: Long,
    ): List<ArticleResponse> {
        return getArticleUseCase.getArticlesByBoardId(boardId)
            .map { ArticleResponse.from(it) }
    }

    @PostMapping
    fun postArticle(
        @RequestBody request: ArticleRequest,
    ): CommandResponse {
        return CommandResponse(createArticleUseCase.createArticle(request).id)
    }

    @PutMapping("{id}")
    fun putArticle(
        @PathVariable id: Long,
        @RequestBody request: ArticleRequest,
    ): CommandResponse {
        return CommandResponse(updateArticleUseCase.updateArticle(id, request).id)
    }

    @DeleteMapping("{id}")
    fun deleteArticle(
        @PathVariable id: Long,
    ) {
        deleteArticleUseCase.deleteArticle(id)
    }
}
