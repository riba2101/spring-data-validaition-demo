package com.example.springdatavalidaitiondemo.repository

import com.example.springdatavalidaitiondemo.entity.Entity
import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport

class ExposedRepositoryConfigExtension : RepositoryConfigurationExtensionSupport() {

    override fun getModuleName(): String {
        return "Exposed"
    }

    override fun getRepositoryFactoryBeanClassName(): String {
        return ExposedRepositoryFactoryBean::class.java.getName()
    }

    override fun getModulePrefix(): String {
        return this.moduleName.lowercase()
    }

    override fun getIdentifyingAnnotations(): Collection<Class<out Annotation>> {
        return listOf(Entity::class.java)
    }

    override fun getIdentifyingTypes(): Collection<Class<*>> {
        return listOf(ExposedRepository::class.java)
    }
}