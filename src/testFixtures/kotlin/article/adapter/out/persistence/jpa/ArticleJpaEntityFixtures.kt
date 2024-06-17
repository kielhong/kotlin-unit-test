package article.adapter.out.persistence.jpa

import com.example.demo.article.adapter.out.jpa.ArticleJpaEntity
import com.example.demo.article.adapter.out.jpa.BoardJpaEntity
import java.time.ZonedDateTime

object ArticleJpaEntityFixtures {
    fun stub(
        id: Long = 1,
        board: BoardJpaEntity = BoardJpaEntityFixtures.stub(),
    ) = ArticleJpaEntity(
        id = id,
        board = board,
        title = "title",
        content = "content",
        createdAt = ZonedDateTime.now(),
        updatedAt = ZonedDateTime.now(),
    )
}
