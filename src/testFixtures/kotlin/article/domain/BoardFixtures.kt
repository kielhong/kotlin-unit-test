package article.domain

import com.example.demo.article.domain.Board

object BoardFixtures {
    fun board() =
        Board(
            id = 1L,
            name = "board",
        )

    fun anotherBoard() =
        Board(
            id = 2L,
            name = "another board",
        )
}
