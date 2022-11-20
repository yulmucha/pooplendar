package pooplendar.community.comment.ui

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pooplendar.community.comment.application.CommentResponse
import pooplendar.community.comment.application.CommentService
import pooplendar.community.comment.application.CreateCommentRequest
import pooplendar.support.ui.ApiResponse
import java.net.URI
import javax.validation.Valid

@RequestMapping("/api/v1/comments")
@RestController
class CommentRestController(
    private val commentService: CommentService,
) {
    @PostMapping
    fun create(@Valid @RequestBody request: CreateCommentRequest): ResponseEntity<ApiResponse<CommentResponse>> {
        val commentResponse = commentService.save(request)
        return ResponseEntity.created(URI.create("/api/v1/comments/${commentResponse.id}"))
            .body(ApiResponse.success(commentResponse))
    }
}
