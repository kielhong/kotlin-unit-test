package article.domain

import com.example.demo.article.domain.Board

object BoardFixtures {
    fun board() =
        Board(
            id = 1L,
            name = "name",
        )
}
