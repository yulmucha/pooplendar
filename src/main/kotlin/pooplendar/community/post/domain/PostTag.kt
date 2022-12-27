package pooplendar.community.post.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class PostTag(
    @Column(nullable = false)
    val tagId: Long,

    val name: String,
)
