package com.example.springdatavalidaitiondemo.repository

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Validated
@NoRepositoryBean
@Transactional(readOnly = true)
interface ExposedRepository<T, ID> :
    CrudRepository<T, ID>
        where ID : Comparable<ID> {

    val entityClass: EntityClass<ID, Entity<ID>>

    @Transactional
    override fun <S : T> saveAll(entities: Iterable<S>): List<S>

}