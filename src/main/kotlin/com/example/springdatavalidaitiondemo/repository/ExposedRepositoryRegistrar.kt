package com.example.springdatavalidaitiondemo.repository

import org.springframework.boot.autoconfigure.data.AbstractRepositoryConfigurationSourceSupport
import org.springframework.data.repository.config.RepositoryConfigurationExtension

class ExposedRepositoryRegistrar : AbstractRepositoryConfigurationSourceSupport() {

    override fun getAnnotation(): Class<out Annotation> {
        return EnableExposedRepositories::class.java
    }

    override fun getConfiguration(): Class<*> {
        return EnableExposedRepositoriesConfiguration::class.java
    }

    override fun getRepositoryConfigurationExtension(): RepositoryConfigurationExtension {
        return ExposedRepositoryConfigExtension()
    }

    @EnableExposedRepositories()
    private open inner class EnableExposedRepositoriesConfiguration private constructor()
}