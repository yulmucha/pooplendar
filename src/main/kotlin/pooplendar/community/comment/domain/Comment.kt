package pooplendar.community.comment.domain

import pooplendar.support.domain.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob

@Entity
class Comment(
    contents: String,
    likeCount: Long = 0L,

    @Column(nullable = false)
    val postId: Long,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    val id: Long = 0L,
) : BaseEntity() {
    @Lob
    var contents: String = contents
        private set

    var likeCount: Long = likeCount
        private set
}
