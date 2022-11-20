package pooplendar.community.board.domain

import org.springframework.data.repository.Repository

interface BoardRepository : Repository<Board, Long> {
    fun save(board: Board): Board
}
