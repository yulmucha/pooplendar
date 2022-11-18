package pooplendar.community.comment.application

import pooplendar.community.comment.domain.Comment
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateCommentRequest(
    @field:NotNull
    val postId: Long?,

    @field:NotBlank
    @field:Size(min = 1, max = 500)
    val contents: String,
)

data class CommentResponse(
    val id: Long,
    val contents: String,
    val likeCount: Long,
    val postId: Long,
) {
    constructor(comment: Comment) : this(
        id = comment.id,
        contents = comment.contents,
        likeCount = comment.likeCount,
        postId = comment.postId,
    )
}
