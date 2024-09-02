package com.example.springdatavalidaitiondemo.repository

import org.jetbrains.exposed.dao.EntityClass
import org.springframework.data.repository.core.EntityInformation
import org.springframework.data.repository.core.RepositoryInformation
import org.springframework.data.repository.core.RepositoryMetadata
import org.springframework.data.repository.core.support.RepositoryFactorySupport

class ExposedRepositoryFactory : RepositoryFactorySupport() {

    override fun <T : Any, ID : Any> getEntityInformation(domainClass: Class<T>): EntityInformation<T, ID> {
        throw UnsupportedOperationException("Not supported")
    }

    override fun getTargetRepository(metadata: RepositoryInformation): Any {
        val companion = metadata
            .domainType
            .declaredClasses
            .first { clazz -> clazz.name.contains("Companion") }
            .constructors
            .first()

        return getRepositoryBaseClass(metadata)
            .getConstructor(EntityClass::class.java)
            .newInstance(companion.newInstance(null))
    }

    override fun getRepositoryBaseClass(metadata: RepositoryMetadata): Class<*> {
        return DefaultExposedRepository::class.java
    }


}