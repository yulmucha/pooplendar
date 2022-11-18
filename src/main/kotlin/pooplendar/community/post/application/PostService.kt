package pooplendar.community.post.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pooplendar.community.post.domain.Post
import pooplendar.community.post.domain.PostRepository

@Transactional(readOnly = true)
@Service
class PostService(
    private val postRepository: PostRepository,
) {
    @Transactional
    fun save(request: CreatePostRequest): PostResponse {
        return postRepository.save(
            Post(
                title = request.title,
                contents = request.contents,
                boardId = request.boardId!!,
            )
        ).let(::PostResponse)
    }
}
