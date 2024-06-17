package com.example.demo.article.adapter.out.jpa

import com.example.demo.article.domain.Board
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class BoardJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
) {
    fun toDomain(): Board =
        Board(
            id = id,
            name = name,
        )
}
