package com.example.springdatavalidaitiondemo.repository

import com.example.springdatavalidaitiondemo.group.Save
import jakarta.validation.Valid
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import java.util.*

@Validated
@Repository
@Transactional(readOnly = true)
open class DefaultExposedRepository<T, ID>(override val entityClass: EntityClass<ID, T>) :
    ExposedRepository<T, ID>
        where T : Entity<ID>,
              ID : Comparable<ID> {

    @Validated(Save::class)
    override fun <S : T> saveAll(@Valid entities: Iterable<S>): List<S> {
        return entities.map { save(it) } //yeah...no
    }

    @Validated(Save::class)
    override fun <S : T> save(@Valid entity: S): S {
        entity.flush()

        return this.entityClass[entity.id.value] as S
    }

    override fun findById(id: ID): Optional<T> {
        return Optional.ofNullable(entityClass.findById(id))
    }

    override fun findAllById(ids: Iterable<ID>): Iterable<T> {
        return entityClass
            .forIds(ids.toList())
    }

    override fun existsById(id: ID): Boolean {
        return findById(id).isPresent
    }

    override fun findAll(): Iterable<T> {
        return entityClass
            .all()
            .toList()
    }

    override fun count(): Long {
        return entityClass
            .table
            .selectAll()
            .count()
    }

    override fun deleteAll() {
        entityClass.table.deleteAll()
    }

    override fun deleteAll(entities: Iterable<T>) {
        this.entityClass.table.deleteWhere(null, null) { _ ->
            entityClass.table.id inList (entities.map { e -> e.id })
        }    }

    override fun deleteAllById(ids: Iterable<ID>) {
        this.entityClass.table.deleteWhere(null, null) { _ ->
            entityClass.table.id inList (ids)
        }
    }

    override fun delete(entity: T) {
        deleteById(entity.id.value)
    }

    override fun deleteById(id: ID) {
        this.entityClass.table.deleteWhere(null, null) {
            entityClass.table.id eq id
        }
    }

}