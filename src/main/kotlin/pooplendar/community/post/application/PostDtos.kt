package pooplendar.community.post.application

import pooplendar.community.post.domain.Post
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreatePostRequest(
    @field:NotNull
    val boardId: Long?,

    @field:NotBlank
    val title: String,

    @field:NotBlank
    val contents: String,

    val tags: List<String>,
)

data class PostResponse(
    val id: Long,
    val title: String,
    val contents: String,
    val viewCount: Long,
    val likeCount: Long,
    val tags: List<String>,
    val boardId: Long,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
) {
    constructor(post: Post) : this(
        id = post.id,
        title = post.title,
        contents = post.contents,
        viewCount = post.viewCount,
        likeCount = post.likeCount,
        tags = post.tags
            .map { it.name },
        boardId = post.boardId,
        createdAt = post.createdDateTime,
        modifiedAt = post.modifiedDateTime,
    )
}
