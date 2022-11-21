package pooplendar.community.post.application

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pooplendar.community.post.domain.Post
import pooplendar.community.post.domain.PostRepository
import pooplendar.community.tag.application.CreateTagRequest
import pooplendar.community.tag.application.TagService

@Transactional(readOnly = true)
@Service
class PostService(
    private val postRepository: PostRepository,
    private val tagService: TagService,
) {
    @Transactional
    fun save(request: CreatePostRequest): PostResponse {
        val post = Post(
            title = request.title,
            contents = request.contents,
            boardId = request.boardId!!,
        )
        val tagResponses = request.tags.map { CreateTagRequest(it) }
            .let { tagService.saveAll(it) }
        post.saveTags(tagResponses.map { it.id })

        return postRepository.save(post)
            .let(::PostResponse)
    }

    fun findAllByBoardId(boardId: Long, offset: Long, size: Int): List<PostResponse> {
        return postRepository.findAllByBoardId(boardId, offset, PageRequest.ofSize(size))
            .map(::PostResponse)
    }
}
