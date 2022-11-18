package pooplendar.community.comment.domain

import org.springframework.data.repository.Repository

interface CommentRepository : Repository<Comment, Long> {
    fun save(comment: Comment): Comment
}
