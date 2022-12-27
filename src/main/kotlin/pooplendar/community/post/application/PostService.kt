package pooplendar.community.post.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pooplendar.community.post.domain.Post
import pooplendar.community.post.domain.PostRepository
import pooplendar.community.post.domain.PostTag
import pooplendar.community.tag.application.TagResponse
import pooplendar.community.post.domain.findAll
import pooplendar.community.post.domain.getById

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

        val tagResponsesByName = tagResponses.associateBy { it.name }
        val postTags = request.tags.map { tag ->
            val tagResponse = tagResponsesByName.getValue(tag.lowercase())
            PostTag(tagId = tagResponse.id, name = tag)
        }
        post.saveTags(postTags)

        return postRepository.save(post)
            .let(::PostResponse)
    }

    fun findAllByBoardId(boardId: Long, offset: Long, size: Int): List<PostResponse> {
        return postRepository.findAll(boardId)
            .map(::PostResponse)
    }

    fun getById(id: Long): PostResponse {
        val post = postRepository.getById(id)
        return PostResponse(post)
    }

    @Transactional
    fun deleteById(id: Long) {
        postRepository.getById(id)
            .softDelete()
    }
}
