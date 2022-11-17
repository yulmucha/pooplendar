package pooplendar.community.board.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pooplendar.community.board.domain.Board
import pooplendar.community.board.domain.BoardRepository

@Transactional(readOnly = true)
@Service
class BoardService(
    private val boardRepository: BoardRepository,
) {
    @Transactional
    fun save(request: CreateBoardRequest): BoardResponse {
        return boardRepository.save(
            Board(
                name = request.name,
            )
        ).let(::BoardResponse)
    }
}
