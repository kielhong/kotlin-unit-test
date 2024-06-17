package article.adapter.out.persistence.jpa

import com.example.demo.article.adapter.out.jpa.ArticleJpaEntity
import java.time.ZonedDateTime

object ArticleJpaEntityFixtures {
    fun stub(id: Long = 1) =
        ArticleJpaEntity(
            id = id,
            board = BoardJpaEntityFixtures.stub(),
            title = "title",
            content = "content",
            createdAt = ZonedDateTime.now(),
            updatedAt = ZonedDateTime.now(),
        )
}
