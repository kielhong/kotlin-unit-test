package com.example.demo.article.adapter.out.jpa

import com.example.demo.article.domain.Article
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.ZonedDateTime

@Entity
data class ArticleJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    val board: BoardJpaEntity,
    @Column(nullable = false)
    val title: String,
    @Column(nullable = false)
    val content: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
) {
    companion object {
        fun from(
            article: Article,
            boardJpaEntity: BoardJpaEntity,
        ): ArticleJpaEntity =
            ArticleJpaEntity(
                id = article.id,
                board = boardJpaEntity,
                title = article.title,
                content = article.content,
                createdAt = ZonedDateTime.now(),
                updatedAt = ZonedDateTime.now(),
            )
    }

    fun toDomain(): Article =
        Article(
            id = id,
            board = board.toDomain(),
            title = title,
            content = content,
        )
}
