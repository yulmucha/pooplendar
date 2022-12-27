package pooplendar.community

import pooplendar.community.post.application.CreatePostRequest
import pooplendar.community.post.application.PostResponse
import pooplendar.community.tag.domain.Tag
import java.time.LocalDateTime

private const val TITLE = "게시 글 제목"
private const val CONTENTS = "게시 글 내용"
private const val VIEW_COUNT = 7979L
private const val LIKE_COUNT = 79L
private val TAG_NAMES = listOf("Kotlin", "SPRING")
private val TAGS = listOf(
    Tag(id = 2L, name = "kotlin"),
    Tag(id = 3L, name = "spring")
)

fun createCreatePostRequest(
    boardId: Long = 1L,
    title: String = TITLE,
    contents: String = CONTENTS,
    tags: List<String> = TAG_NAMES,
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
    tags: List<String> = TAG_NAMES,
    boardId: Long = 1L,
    createdAt: LocalDateTime = LocalDateTime.now(),
    modifiedAt: LocalDateTime = LocalDateTime.now(),
) = PostResponse(
    id = id,
    title = title,
    contents = contents,
    viewCount = viewCount,
    likeCount = likeCount,
    tags = tags,
    boardId = boardId,
    createdAt = createdAt,
    modifiedAt = modifiedAt,
)
