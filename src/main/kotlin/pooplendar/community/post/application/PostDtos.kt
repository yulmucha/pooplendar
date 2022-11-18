package pooplendar.community.post.application

import pooplendar.community.post.domain.Post
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreatePostRequest(
    @field:NotNull
    val boardId: Long?,

    @field:NotBlank
    val title: String,

    @field:NotBlank
    val contents: String,
)

data class PostResponse(
    val id: Long,
    val title: String,
    val contents: String,
    val viewCount: Long,
    val likeCount: Long,
    val tagIds: Set<Long>,
    val boardId: Long,
) {
    constructor(post: Post) : this(
        id = post.id,
        title = post.title,
        contents = post.contents,
        viewCount = post.viewCount,
        likeCount = post.likeCount,
        tagIds = post.tags
            .map { it.tagId }
            .toSet(),
        boardId = post.boardId,
    )
}
