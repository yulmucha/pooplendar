package pooplendar.community.board

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
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
        }.andDo {
            handle(
                MockMvcRestDocumentation.document(
                    "board-create",
                    PayloadDocumentation.requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING)
                            .description("게시판 이름"),
                    ),
                    PayloadDocumentation.responseFields(
                        fieldWithPath("message").type(JsonFieldType.STRING)
                            .description(""),
                        fieldWithPath("body").type(JsonFieldType.OBJECT)
                            .description(""),
                        fieldWithPath("body.id").type(JsonFieldType.NUMBER)
                            .description("게시판 아이디"),
                        fieldWithPath("body.name").type(JsonFieldType.STRING)
                            .description("게시판 이름"),
                    )
                )
            )
        }
    }
}
