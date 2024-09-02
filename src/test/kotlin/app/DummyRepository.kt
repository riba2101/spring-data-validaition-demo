package app

import com.example.springdatavalidaitiondemo.repository.ExposedRepository

interface DummyRepository : ExposedRepository<DummyEntity, ULong>