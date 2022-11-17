package pooplendar.community

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class CommunityApplication

fun main(args: Array<String>) {
    runApplication<CommunityApplication>(*args)
}
