package pooplendar.community.tag.application

import pooplendar.community.tag.domain.Tag
import java.time.LocalDateTime

data class CreateTagRequest(
    val name: String,
)

data class TagResponse(
    val id: Long,
    val name: String,
    val createdAt: LocalDateTime,
) {
    constructor(tag: Tag) : this(
        id = tag.id,
        name = tag.name,
        createdAt = tag.createdDateTime,
    )
}
