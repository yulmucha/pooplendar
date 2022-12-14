package pooplendar.community.post.application

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pooplendar.community.post.domain.Post
import pooplendar.community.post.domain.PostRepository
import pooplendar.community.tag.application.TagResponse

@Transactional(readOnly = true)
@Service
class PostService(
    private val postRepository: PostRepository,
) {
    @Transactional
    fun save(request: CreatePostRequest, tagResponses: List<TagResponse>): PostResponse {
        val post = Post(
            title = request.title,
            contents = request.contents,
            boardId = request.boardId!!,
        )
        post.saveTags(tagResponses.map { it.id })

        return postRepository.save(post)
            .let(::PostResponse)
    }

    fun findAllByBoardId(boardId: Long, offset: Long, size: Int): List<PostResponse> {
        return postRepository.findAllByBoardId(boardId, offset, PageRequest.ofSize(size))
            .map(::PostResponse)
    }

    fun getById(id: Long): PostResponse {
        val post = postRepository.findById(id)
            ?: throw NoSuchElementException("게시 글을 찾을 수 없습니댜. id: $id")
        return PostResponse(post)
    }
}
