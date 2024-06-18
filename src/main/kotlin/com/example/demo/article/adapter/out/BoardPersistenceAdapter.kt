package com.example.demo.article.adapter.out

import com.example.demo.article.adapter.out.jpa.BoardJpaRepository
import com.example.demo.article.application.port.out.LoadBoardPort
import com.example.demo.article.domain.Board
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class BoardPersistenceAdapter(
    private val boardJpaRepository: BoardJpaRepository,
) : LoadBoardPort {
    override fun findBoardById(id: Long): Board? {
        return boardJpaRepository.findByIdOrNull(id)
            ?.toDomain()
    }
}
