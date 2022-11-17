package pooplendar.community.board.application

import pooplendar.community.board.domain.Board
import javax.validation.constraints.NotBlank

data class CreateBoardRequest(
    @field:NotBlank
    val name: String,
)

data class BoardResponse(
    val id: Long,
    val name: String,
) {
    constructor(board: Board) : this(
        id = board.id,
        name = board.name,
    )
}
