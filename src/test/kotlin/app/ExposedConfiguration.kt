package app

import org.jetbrains.exposed.spring.SpringTransactionManager
import org.jetbrains.exposed.spring.autoconfigure.ExposedAutoConfiguration
import org.jetbrains.exposed.sql.DatabaseConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

@SpringBootApplication(exclude = [ExposedAutoConfiguration::class])
open class ExposedConfiguration {

    @Bean
    open fun transactionManager(datasource: DataSource, databaseConfig: DatabaseConfig): SpringTransactionManager {
        return SpringTransactionManager(datasource, databaseConfig, true)
    }

    /**
     * Database config with default values
     */
    @Bean
    @ConditionalOnMissingBean(DatabaseConfig::class)
    open fun databaseConfig(): DatabaseConfig {
        return DatabaseConfig {}
    }

}