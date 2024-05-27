package com.example.demo.article.domain

class Article(
    val id: Long,
    var board: Board,
    var title: String,
    var content: String,
) {
    fun update(
        board: Board,
        title: String,
        content: String,
    ) {
        this.board = board
        this.title = title
        this.content = content
    }
}
