package app

import jakarta.validation.ConstraintViolationException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

@ExposedTest(
    classes = [ExposedConfiguration::class, TestConfiguration::class],
    initializers = [PostgresSqlContextInitializer::class]
)
@Transactional
open class ExposedValidationIT {

    @Autowired
    private lateinit var repository: DummyRepository

    @Autowired
    private lateinit var service: DummyService

    @BeforeEach
    open fun setUp() {
        repository.deleteAll()
    }

    @Nested
    open inner class Repo {

        @Test
        fun shouldNotThrowOnValid() {
            val exception = Assertions.catchException {
                val entity = DummyEntity.new {
                    name = "test"
                    createdAt = OffsetDateTime.now()
                }
                repository.save(entity)
            }

            Assertions.assertThat(exception).isNull()
        }

        @Test
        fun shouldValidateGroup() {
            val entity = DummyEntity.new {
                name = "test"
                createdAt = OffsetDateTime.now()
            }
            val saved = repository.save(entity)


            val exception = Assertions.catchException {
                saved.name = ""

                repository.save(saved)
            }

            Assertions.assertThat(exception).isInstanceOf(ConstraintViolationException::class.java)
        }

    }

    @Nested
    open inner class Service {

        @Test
        fun shouldNotThrowOnValid() {
            val exception = Assertions.catchException {
                val entity = DummyEntity.new {
                    name = "test"
                    createdAt = OffsetDateTime.now()
                }
                service.save(entity)
            }

            Assertions.assertThat(exception).isNull()
        }

        @Test
        fun shouldValidateGroup() {
            val entity = DummyEntity.new {
                name = "test"
                createdAt = OffsetDateTime.now()
            }
            val saved = service.save(entity)


            val exception = Assertions.catchException {
                saved.name = ""

                service.save(saved)
            }

            Assertions.assertThat(exception).isInstanceOf(ConstraintViolationException::class.java)
        }

    }

}
