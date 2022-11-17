package pooplendar.community

import pooplendar.community.board.application.BoardResponse
import pooplendar.community.board.application.CreateBoardRequest

private const val NAME = "쾌변을 위한 팁"

fun createCreateBoardRequest(
    name: String = NAME,
) = CreateBoardRequest(
    name = name,
)

fun createBoardResponse(
    id: Long = 1L,
    name: String = NAME,
) = BoardResponse(
    id = id,
    name = name,
)
