package pooplendar.community.post.domain

import org.springframework.data.repository.Repository

fun PostRepository.getById(id: Long) = findByDeletedDateTimeIsNullAndId(id)
    ?: throw NoSuchElementException("게시 글(id: $id)을 찾을 수 없습니다.")

fun PostRepository.findAll(boardId: Long) = findAllByDeletedDateTimeIsNullAndBoardId(boardId)

interface PostRepository : Repository<Post, Long> {
    fun save(post: Post): Post

    fun findAllByDeletedDateTimeIsNullAndBoardId(boardId: Long): List<Post>

    fun findByDeletedDateTimeIsNullAndId(id: Long): Post?
}
