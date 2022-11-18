package pooplendar.community.post

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.web.servlet.post
import pooplendar.community.RestControllerTest
import pooplendar.community.createCreatePostRequest
import pooplendar.community.createPostResponse
import pooplendar.community.post.application.PostService
import pooplendar.community.post.ui.PostRestController

@WebMvcTest(PostRestController::class)
class PostRestControllerTest : RestControllerTest() {

    @MockkBean
    lateinit var postService: PostService

    @Test
    fun `게시판에 게시 글을 등록한다`() {
        val response = createPostResponse(
            viewCount = 0L,
            likeCount = 0L,
        )
        every { postService.save(any()) } returns response

        mockMvc.post("/api/v1/posts") {
            jsonContent(createCreatePostRequest())
        }.andExpect {
            status { isCreated() }
            content { success(response) }
        }.andDo {
            handle(
                MockMvcRestDocumentation.document(
                    "post-create",
                    PayloadDocumentation.requestFields(
                        PayloadDocumentation.fieldWithPath("boardId").type(JsonFieldType.NUMBER)
                            .description("게시판 ID"),
                        PayloadDocumentation.fieldWithPath("title").type(JsonFieldType.STRING)
                            .description("게시 글 제목"),
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
                        PayloadDocumentation.fieldWithPath("body.title").type(JsonFieldType.STRING)
                            .description("게시 글 제목"),
                        PayloadDocumentation.fieldWithPath("body.contents").type(JsonFieldType.STRING)
                            .description("게시 글 내용"),
                        PayloadDocumentation.fieldWithPath("body.viewCount").type(JsonFieldType.NUMBER)
                            .description("조회 수"),
                        PayloadDocumentation.fieldWithPath("body.likeCount").type(JsonFieldType.NUMBER)
                            .description("좋아요 수"),
                        PayloadDocumentation.fieldWithPath("body.tagIds").type(JsonFieldType.ARRAY)
                            .description("게시 글에 포함된 태그 ID 배열"),
                        PayloadDocumentation.fieldWithPath("body.boardId").type(JsonFieldType.NUMBER)
                            .description("게시판 ID"),
                    )
                )
            )
        }
    }
}
