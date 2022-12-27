package pooplendar.community

import pooplendar.community.tag.application.TagResponse
import java.time.LocalDateTime

fun createTagResponse(
    id: Long = 1L,
    name: String = "spring",
    createdAt: LocalDateTime = LocalDateTime.now(),
) = TagResponse(
    id = id,
    name = name,
    createdAt = createdAt,
)
