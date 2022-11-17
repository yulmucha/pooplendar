package pooplendar.community.board.ui

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pooplendar.community.board.application.BoardResponse
import pooplendar.community.board.application.BoardService
import pooplendar.community.board.application.CreateBoardRequest
import pooplendar.support.ui.ApiResponse
import java.net.URI
import javax.validation.Valid

@RequestMapping("/api/v1/boards")
@RestController
class BoardRestController(
    private val boardService: BoardService,
) {
    @PostMapping
    fun create(@Valid @RequestBody request: CreateBoardRequest): ResponseEntity<ApiResponse<BoardResponse>> {
        val boardResponse = boardService.save(request)
        return ResponseEntity.created(URI.create("/api/v1/boards/${boardResponse.id}"))
            .body(ApiResponse.success(boardResponse))
    }
}
