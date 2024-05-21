package com.example.demo.article.adapter.`in`.api

import com.example.demo.article.adapter.`in`.api.dto.ArticleResponse
import com.example.demo.article.adapter.`in`.api.exception.DomainNotFoundException
import com.example.demo.article.application.port.`in`.GetArticleUseCase
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/articles")
class ArticleController(
    private val getArticleUseCase: GetArticleUseCase,
) {
    @RequestMapping("/{id}")
    fun getArticle(
        @PathVariable id: Long,
    ): ArticleResponse {
        return getArticleUseCase.getArticle(id)
            ?.let { ArticleResponse.from(it) }
            ?: throw DomainNotFoundException("id : $id 에 해당하는 게시글이 없습니다.")
    }
}
