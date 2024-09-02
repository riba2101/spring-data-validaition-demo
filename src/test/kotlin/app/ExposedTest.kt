package app

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContextInitializer
import org.springframework.core.annotation.AliasFor
import org.springframework.test.context.ContextConfiguration
import java.lang.annotation.Inherited
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
@ContextConfiguration
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(
    FlywayExtension::class,
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ImportAutoConfiguration
annotation class ExposedTest(
    @get:AliasFor(
        annotation = ContextConfiguration::class,
        attribute = "classes"
    ) val classes: Array<KClass<*>> = [],
    @get:AliasFor(
        annotation = ContextConfiguration::class,
        attribute = "initializers"
    ) val initializers: Array<KClass<out ApplicationContextInitializer<*>>> = [PostgresSqlContextInitializer::class]
)
