package pooplendar.community.board.application

import pooplendar.community.board.domain.Board
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class CreateBoardRequest(
    @field:NotBlank
    val name: String,
)

data class BoardResponse(
    val id: Long,
    val name: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
) {
    constructor(board: Board) : this(
        id = board.id,
        name = board.name,
        createdAt = board.createdDateTime,
        modifiedAt = board.modifiedDateTime,
    )
}
