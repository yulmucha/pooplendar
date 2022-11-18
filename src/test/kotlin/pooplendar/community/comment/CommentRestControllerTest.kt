package pooplendar.community.comment

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.web.servlet.post
import pooplendar.community.RestControllerTest
import pooplendar.community.comment.application.CommentService
import pooplendar.community.comment.ui.CommentRestController
import pooplendar.community.createCommentResponse
import pooplendar.community.createCreateCommentRequest

@WebMvcTest(CommentRestController::class)
class CommentRestControllerTest : RestControllerTest() {

    @MockkBean
    lateinit var commentService: CommentService

    @Test
    fun `게시 글에 댓글을 단다`() {
        val response = createCommentResponse()
        every { commentService.save(any()) } returns response

        mockMvc.post("/api/v1/comments") {
            jsonContent(createCreateCommentRequest())
        }.andExpect {
            status { isCreated() }
            content { success(response) }
        }.andDo {
            handle(
                MockMvcRestDocumentation.document(
                    "comment-create",
                    PayloadDocumentation.requestFields(
                        PayloadDocumentation.fieldWithPath("postId").type(JsonFieldType.NUMBER)
                            .description("게시판 ID"),
                        PayloadDocumentation.fieldWithPath("contents").type(JsonFieldType.STRING)
                            .description("게시 글 내용"),
                    ),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING)
                            .description(""),
                        PayloadDocumentation.fieldWithPath("body").type(JsonFieldType.OBJECT)
                            .description(""),
                        PayloadDocumentation.fieldWithPath("body.id").type(JsonFieldType.NUMBER)
                            .description("게시 글 ID"),
                        PayloadDocumentation.fieldWithPath("body.contents").type(JsonFieldType.STRING)
                            .description("게시 글 내용"),
                        PayloadDocumentation.fieldWithPath("body.likeCount").type(JsonFieldType.NUMBER)
                            .description("게시 글 내용"),
                        PayloadDocumentation.fieldWithPath("body.postId").type(JsonFieldType.NUMBER)
                            .description("게시판 ID"),
                    )
                )
            )
        }
    }
}
