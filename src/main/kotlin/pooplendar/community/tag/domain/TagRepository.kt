package pooplendar.community.tag.domain

import org.springframework.data.repository.Repository

interface TagRepository : Repository<Tag, Long> {
    fun saveAll(entities: Iterable<Tag>): List<Tag>
    fun findByNameIn(names: List<String>): List<Tag>
}
