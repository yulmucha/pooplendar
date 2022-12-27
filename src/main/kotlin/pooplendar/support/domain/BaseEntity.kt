package pooplendar.support.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class BaseEntity(

    @CreatedDate
    @Column(nullable = false)
    var createdDateTime: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    var modifiedDateTime: LocalDateTime = LocalDateTime.now()
) {
    var deletedDateTime: LocalDateTime? = null
        protected set
}
