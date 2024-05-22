package com.example.demo.article.application.port.out

import com.example.demo.article.domain.Article

interface LoadArticlePort {
    fun findArticleById(id: Long): Article?

    fun findArticlesByBoardId(boardId: Long): List<Article>
}
