package app

import org.laganini.lagano.testcontainers.mariadb.PostgreSqlJdbcContainer
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
open class PostgresSqlContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        //psql log
        SQL_CONTAINER.setCommand("postgres", "-c", "fsync=off", "-c", "log_statement=all")
        SQL_CONTAINER.start()

        val values: TestPropertyValues = TestPropertyValues.of(buildProperties())
        values.applyTo(applicationContext)
    }

    protected open fun buildProperties(): Map<String, String> {
        val props: MutableMap<String, String> = HashMap()
        props["spring.datasource.url"] = SQL_CONTAINER.jdbcUrl
        props["spring.datasource.username"] = SQL_CONTAINER.username
        props["spring.datasource.password"] = SQL_CONTAINER.password

        props["spring.flyway.url"] = SQL_CONTAINER.jdbcUrl
        props["spring.flyway.user"] = SQL_CONTAINER.password
        props["spring.flyway.password"] = SQL_CONTAINER.username

        return props
    }

    companion object {
        @JvmField
        @Container
        val SQL_CONTAINER: PostgreSqlJdbcContainer = PostgreSqlJdbcContainer(
            DockerImageName
                .parse(PostgreSQLContainer.IMAGE)
                .withTag("16.3")
        )
    }

}