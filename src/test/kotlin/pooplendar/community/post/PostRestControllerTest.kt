package pooplendar.community.post

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.test.web.servlet.get
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
                        fieldWithPath("boardId").type(JsonFieldType.NUMBER)
                            .description("게시판 ID"),
                        fieldWithPath("title").type(JsonFieldType.STRING)
                            .description("게시 글 제목"),
                        fieldWithPath("contents").type(JsonFieldType.STRING)
                            .description("게시 글 내용"),
                        fieldWithPath("tags").type(JsonFieldType.ARRAY)
                            .description("게시 글 태그 배열"),
                    ),
                    PayloadDocumentation.responseFields(
                        fieldWithPath("message").type(JsonFieldType.STRING)
                            .description(""),
                        fieldWithPath("body").type(JsonFieldType.OBJECT)
                            .description(""),
                        fieldWithPath("body.id").type(JsonFieldType.NUMBER)
                            .description("게시 글 ID"),
                        fieldWithPath("body.title").type(JsonFieldType.STRING)
                            .description("게시 글 제목"),
                        fieldWithPath("body.contents").type(JsonFieldType.STRING)
                            .description("게시 글 내용"),
                        fieldWithPath("body.viewCount").type(JsonFieldType.NUMBER)
                            .description("조회 수"),
                        fieldWithPath("body.likeCount").type(JsonFieldType.NUMBER)
                            .description("좋아요 수"),
                        fieldWithPath("body.tagIds").type(JsonFieldType.ARRAY)
                            .description("게시 글에 포함된 태그 ID 배열"),
                        fieldWithPath("body.boardId").type(JsonFieldType.NUMBER)
                            .description("게시판 ID"),
                        fieldWithPath("body.createdAt").type(JsonFieldType.STRING)
                            .description("생성된 날짜와 시간 정보입니다. ISO 8601 형식인 yyyy-MM-dd'T'HH:mm:ss.SSS±hh:mm으로 돌아옵니다."),
                        fieldWithPath("body.modifiedAt").type(JsonFieldType.STRING)
                            .description("최종 수정된 날짜와 시간 정보입니다. ISO 8601 형식인 yyyy-MM-dd'T'HH:mm:ss.SSS±hh:mm으로 돌아옵니다."),
                    )
                )
            )
        }
    }

    @Test
    fun `특정 게시판의 게시 글 목록을 조회한다`() {
        val responses = listOf(
            createPostResponse(id = 3L),
            createPostResponse(id = 4L),
            createPostResponse(id = 5L),
            createPostResponse(id = 6L),
            createPostResponse(id = 7L),
        )
        every { postService.findAllByBoardId(any(), any(), any()) } returns responses

        mockMvc.get("/api/v1/posts?boardId={boardId}&offset={offset}&size={size}", 1L, 2L, 5) {
        }.andExpect {
            status { isOk() }
            content { success(responses) }
        }.andDo {
            handle(
                MockMvcRestDocumentation.document(
                    "post-find-all",
                    RequestDocumentation.requestParameters(
                        parameterWithName("boardId")
                            .description("게시판의 ID입니다."),
                        parameterWithName("offset")
                            .description("").optional(),
                        parameterWithName("size")
                            .description("").optional(),
                    ),
                    PayloadDocumentation.responseFields(
                        fieldWithPath("message").type(JsonFieldType.STRING)
                            .description(""),
                        fieldWithPath("body").type(JsonFieldType.ARRAY)
                            .description(""),
                        fieldWithPath("body[].id").type(JsonFieldType.NUMBER)
                            .description("게시 글 ID"),
                        fieldWithPath("body[].title").type(JsonFieldType.STRING)
                            .description("게시 글 제목"),
                        fieldWithPath("body[].contents").type(JsonFieldType.STRING)
                            .description("게시 글 내용"),
                        fieldWithPath("body[].viewCount").type(JsonFieldType.NUMBER)
                            .description("조회 수"),
                        fieldWithPath("body[].likeCount").type(JsonFieldType.NUMBER)
                            .description("좋아요 수"),
                        fieldWithPath("body[].tagIds").type(JsonFieldType.ARRAY)
                            .description("게시 글에 포함된 태그 ID 배열"),
                        fieldWithPath("body[].boardId").type(JsonFieldType.NUMBER)
                            .description("게시판 ID"),
                        fieldWithPath("body[].createdAt").type(JsonFieldType.STRING)
                            .description("생성된 날짜와 시간 정보입니다. ISO 8601 형식인 yyyy-MM-dd'T'HH:mm:ss.SSS±hh:mm으로 돌아옵니다."),
                        fieldWithPath("body[].modifiedAt").type(JsonFieldType.STRING)
                            .description("최종 수정된 날짜와 시간 정보입니다. ISO 8601 형식인 yyyy-MM-dd'T'HH:mm:ss.SSS±hh:mm으로 돌아옵니다."),
                    )
                )
            )
        }
    }
}
