package article.adapter.out.persistence.jpa

import com.example.demo.article.adapter.out.jpa.BoardJpaEntity

object BoardJpaEntityFixtures {
    fun stub(id: Long = 1) =
        BoardJpaEntity(
            id = id,
            name = "board",
        )
}
