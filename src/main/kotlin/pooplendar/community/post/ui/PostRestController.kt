package pooplendar.community.post.ui

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pooplendar.community.post.application.CreatePostRequest
import pooplendar.community.post.application.PostResponse
import pooplendar.community.post.application.PostService
import pooplendar.support.ui.ApiResponse
import java.net.URI
import javax.validation.Valid
import javax.validation.constraints.Min

@Validated
@RequestMapping("/api/v1/posts")
@RestController
class PostRestController(
    private val postService: PostService,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun create(@Valid @RequestBody request: CreatePostRequest): ResponseEntity<ApiResponse<PostResponse>> {
        logger.info("게시 글 생성 요청 \n\t $request")
        val postResponse = postService.save(request)
        logger.info("게시 글 생성 완료 \n\t $postResponse")
        return ResponseEntity.created(URI.create("/api/v1/posts/${postResponse.id}"))
            .body(ApiResponse.success(postResponse))
    }

    @GetMapping
    fun findAll(
        @RequestParam boardId: Long,
        @RequestParam(required = false, defaultValue = "0") @Min(0) offset: Long,
        @RequestParam(required = false, defaultValue = "20") size: Int,
    ): ApiResponse<List<PostResponse>> {
        return ApiResponse.success(postService.findAllByBoardId(boardId, offset, size))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ApiResponse<PostResponse> {
        return ApiResponse.success(postService.getById(id))
    }
}
