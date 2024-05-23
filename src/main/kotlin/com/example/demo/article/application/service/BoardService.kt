package com.example.demo.article.application.service

import com.example.demo.article.application.port.out.LoadBoardPort
import com.example.demo.article.domain.Board
import org.springframework.stereotype.Service

@Service
class BoardService : LoadBoardPort {
    override fun findBoardById(id: Long): Board? {
        TODO("Not yet implemented")
    }
}
