package app

import com.example.springdatavalidaitiondemo.group.Default
import com.example.springdatavalidaitiondemo.group.Save
import jakarta.validation.Valid
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
class DummyService(private val dummyRepository: DummyRepository) {

    @Validated(Default::class)
    fun find(@Valid dummy: DummyEntity): DummyEntity? {
        return dummyRepository.findByIdOrNull(dummy.id.value)
    }

    @Validated(Save::class)
    fun save(@Valid dummy: DummyEntity): DummyEntity {
        return dummyRepository.save(dummy)
    }

}