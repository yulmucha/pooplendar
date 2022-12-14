package pooplendar.community.tag.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pooplendar.community.tag.domain.Tag
import pooplendar.community.tag.domain.TagRepository

@Transactional(readOnly = true)
@Service
class TagService(
    private val tagRepository: TagRepository,
) {
    @Transactional
    fun saveAll(requests: List<CreateTagRequest>): List<TagResponse> {
        val tagNames = requests.map { it.name.lowercase() }

        val existingTags = tagRepository.findByNameIn(tagNames)
        val existingTagNames = existingTags.map { it.name }

        val newTagNames = tagNames.filter { !existingTagNames.contains(it) }
        val newTags = tagRepository.saveAll(newTagNames.map { Tag(it) })

        return (existingTags + newTags).map(::TagResponse)
    }
}
