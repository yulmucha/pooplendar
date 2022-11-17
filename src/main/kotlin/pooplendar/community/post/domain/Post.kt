package pooplendar.community.post.domain

import pooplendar.support.domain.BaseEntity
import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Lob

@Entity
class Post(
    title: String,
    contents: String,
    viewCount: Long = 0L,
    likeCount: Long = 0L,

    @Column(nullable = false)
    val boardId: Long,

    @ElementCollection
    @CollectionTable(name = "post_tag", joinColumns = [JoinColumn(name = "post_id")])
    val _tags: MutableList<PostTag> = mutableListOf(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    val id: Long = 0L,
) : BaseEntity() {
    var title: String = title
        private set

    @Lob
    var contents: String = contents
        private set

    var viewCount: Long = viewCount
        private set

    var likeCount: Long = likeCount
        private set

    val tag: List<PostTag>
        get() = _tags
}
