package app

import com.example.springdatavalidaitiondemo.repository.EnableExposedRepositories
import org.springframework.context.annotation.Configuration

@Configuration
@EnableExposedRepositories(basePackages = ["app"])
class TestConfiguration : ExposedTestConfiguration()