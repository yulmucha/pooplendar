package pooplendar.community

import pooplendar.community.comment.application.CommentResponse
import pooplendar.community.comment.application.CreateCommentRequest

private const val CONTENTS = "댓글 내용"
private const val LIKE_COUNT = 79L

fun createCreateCommentRequest(
    postId: Long = 1L,
    contents: String = CONTENTS,
) = CreateCommentRequest(
    postId = postId,
    contents = contents,
)

fun createCommentResponse(
    id: Long = 1L,
    contents: String = CONTENTS,
    likeCount: Long = LIKE_COUNT,
    postId: Long = 1L,
) = CommentResponse(
    id = id,
    contents = contents,
    likeCount = likeCount,
    postId = postId,
)
