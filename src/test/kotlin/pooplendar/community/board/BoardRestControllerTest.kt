package pooplendar.community.board

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.post
import pooplendar.community.RestControllerTest
import pooplendar.community.board.application.BoardService
import pooplendar.community.board.ui.BoardRestController
import pooplendar.community.createBoardResponse
import pooplendar.community.createCreateBoardRequest

@WebMvcTest(BoardRestController::class)
class BoardRestControllerTest : RestControllerTest() {

    @MockkBean
    lateinit var boardService: BoardService

    @Test
    fun `게시판을 만든다`() {
        val response = createBoardResponse()
        every { boardService.save(any()) } returns response

        mockMvc.post("/api/v1/boards") {
            jsonContent(createCreateBoardRequest())
        }.andExpect {
            status { isCreated() }
            content { success(response) }
        }
    }
}
