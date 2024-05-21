package com.example.demo.article.adapter.`in`.api.dto

import com.example.demo.article.domain.Board

data class BoardResponse(
    val id: Long,
    val name: String,
) {
    companion object {
        fun from(board: Board) =
            BoardResponse(
                id = board.id,
                name = board.name,
            )
    }
}
