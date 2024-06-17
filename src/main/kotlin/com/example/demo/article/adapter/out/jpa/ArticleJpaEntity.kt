package com.example.demo.article.adapter.out.jpa

import com.example.demo.article.domain.Article
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.ZonedDateTime

@Entity
data class ArticleJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    val board: BoardJpaEntity,
    @Column(nullable = false)
    val title: String,
    @Column(nullable = false)
    val content: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
) {
    fun toDomain() =
        Article(
            id = id,
            board = board.toDomain(),
            title = title,
            content = content,
        )
}
