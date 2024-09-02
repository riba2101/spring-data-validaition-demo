package com.example.springdatavalidaitiondemo.repository

import org.springframework.data.repository.Repository
import org.springframework.data.repository.core.support.RepositoryFactorySupport
import org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport

class ExposedRepositoryFactoryBean<T : Repository<S, ID>, S, ID>(repositoryInterface: Class<out T>) :
    TransactionalRepositoryFactoryBeanSupport<T, S, ID>(repositoryInterface) {

    override fun doCreateRepositoryFactory(): RepositoryFactorySupport {
        return ExposedRepositoryFactory()
    }

}
