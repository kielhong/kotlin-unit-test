package com.example.demo.article.domain

class Article(
    val id: Long? = null,
    val board: Board,
    val title: String,
    val content: String,
)
