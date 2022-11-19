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
        val tagNames = requests.map { it.name }
            .map { name ->
                name.filter { !it.isWhitespace() }
                    .lowercase()
            }
        val tagsByName = tagRepository.findByNameIn(tagNames)
            .associateBy { it.name }

        val existingTags = tagNames.filter { tagsByName[it] != null }
            .map { tagsByName.getValue(it) }
        val newTags = tagNames.filter { tagsByName[it] == null }
            .map { Tag(it) }
            .let { tagRepository.saveAll(it) }

        return (existingTags + newTags).map(::TagResponse)
    }
}
