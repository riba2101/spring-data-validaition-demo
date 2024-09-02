package app

import org.flywaydb.core.Flyway
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.test.context.junit.jupiter.SpringExtension

class FlywayExtension : BeforeAllCallback {
    override fun beforeAll(context: ExtensionContext) {
        val flyway: Flyway = SpringExtension.getApplicationContext(context).getBean(Flyway::class.java)
        flyway.migrate()
    }
}
