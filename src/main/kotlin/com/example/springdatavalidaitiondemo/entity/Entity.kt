package com.example.springdatavalidaitiondemo.entity


@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Entity(
    val name: String = "",
)
