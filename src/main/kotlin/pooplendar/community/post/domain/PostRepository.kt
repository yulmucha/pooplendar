package pooplendar.community.post.domain

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository

interface PostRepository : Repository<Post, Long> {
    fun save(post: Post): Post

    @Query("select p from Post p where p.boardId = :boardId and p.id > :offset")
    fun findAllByBoardId(boardId: Long, offset: Long, pageable: Pageable): List<Post>

    fun findById(id: Long): Post?
}
