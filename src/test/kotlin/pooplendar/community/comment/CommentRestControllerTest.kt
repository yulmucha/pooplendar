package pooplendar.community.comment

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
                        fieldWithPath("postId").type(JsonFieldType.NUMBER)
                            .description("게시 글 ID"),
                        fieldWithPath("contents").type(JsonFieldType.STRING)
                            .description("댓글 내용"),
                    ),
                    PayloadDocumentation.responseFields(
                        fieldWithPath("message").type(JsonFieldType.STRING)
                            .description(""),
                        fieldWithPath("body").type(JsonFieldType.OBJECT)
                            .description(""),
                        fieldWithPath("body.id").type(JsonFieldType.NUMBER)
                            .description("댓글 ID"),
                        fieldWithPath("body.contents").type(JsonFieldType.STRING)
                            .description("댓글 내용"),
                        fieldWithPath("body.likeCount").type(JsonFieldType.NUMBER)
                            .description("좋아요 수"),
                        fieldWithPath("body.postId").type(JsonFieldType.NUMBER)
                            .description("게시 글 ID"),
                        fieldWithPath("body.createdAt").type(JsonFieldType.STRING)
                            .description("생성된 날짜와 시간 정보입니다. ISO 8601 형식인 yyyy-MM-dd'T'HH:mm:ss.SSS±hh:mm으로 돌아옵니다."),
                        fieldWithPath("body.modifiedAt").type(JsonFieldType.STRING)
                            .description("최종 수정된 날짜와 시간 정보입니다. ISO 8601 형식인 yyyy-MM-dd'T'HH:mm:ss.SSS±hh:mm으로 돌아옵니다."),
                    )
                )
            )
        }
    }
}
