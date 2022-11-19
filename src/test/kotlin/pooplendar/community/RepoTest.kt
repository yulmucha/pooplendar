package pooplendar.community

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import pooplendar.community.tag.domain.Tag
import pooplendar.community.tag.domain.TagRepository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@ActiveProfiles("test")
@DataJpaTest
class RepoTest {

    @Autowired
    lateinit var tagRepository: TagRepository

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Test
    fun `aa`() {
        tagRepository.saveAll(listOf(Tag("a"), Tag("b")))

        entityManager.flush()
        entityManager.clear()

        tagRepository.findByName("a")
        tagRepository.findByName("b")
    }
}
