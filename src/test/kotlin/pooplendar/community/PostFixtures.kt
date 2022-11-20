package pooplendar.community

import pooplendar.community.post.application.CreatePostRequest
import pooplendar.community.post.application.PostResponse
import pooplendar.community.tag.domain.Tag
import java.time.LocalDateTime

private const val TITLE = "게시 글 제목"
private const val CONTENTS = "게시 글 내용"
private const val VIEW_COUNT = 7979L
private const val LIKE_COUNT = 79L
private val TAGS = setOf(
    Tag(id = 2L, name = "유산균"),
    Tag(id = 3L, name = "습관")
)

fun createCreatePostRequest(
    boardId: Long = 1L,
    title: String = TITLE,
    contents: String = CONTENTS,
    tags: List<String> = TAGS.map { it.name },
) = CreatePostRequest(
    boardId = boardId,
    title = title,
    contents = contents,
    tags = tags,
)

fun createPostResponse(
    id: Long = 1L,
    title: String = TITLE,
    contents: String = CONTENTS,
    viewCount: Long = VIEW_COUNT,
    likeCount: Long = LIKE_COUNT,
    tagIds: Set<Long> = TAGS.map { it.id }.toSet(),
    boardId: Long = 1L,
    createdAt: LocalDateTime = LocalDateTime.now(),
    modifiedAt: LocalDateTime = LocalDateTime.now(),
) = PostResponse(
    id = id,
    title = title,
    contents = contents,
    viewCount = viewCount,
    likeCount = likeCount,
    tagIds = tagIds,
    boardId = boardId,
    createdAt = createdAt,
    modifiedAt = modifiedAt,
)
