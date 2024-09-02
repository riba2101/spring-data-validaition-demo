package app

import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(FlywayAutoConfiguration::class)
class ExposedTestConfiguration