package com.example.demo.article.application.port.out

import com.example.demo.article.domain.Board

interface LoadBoardPort {
    fun findBoardById(id: Long): Board?
}
