package pooplendar.community.comment.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pooplendar.community.comment.domain.Comment
import pooplendar.community.comment.domain.CommentRepository

@Transactional(readOnly = true)
@Service
class CommentService(
    private val commentRepository: CommentRepository,
) {
    @Transactional
    fun save(request: CreateCommentRequest): CommentResponse {
        return commentRepository.save(
            Comment(
                contents = request.contents,
                postId = request.postId!!,
            )
        ).let(::CommentResponse)
    }
}
