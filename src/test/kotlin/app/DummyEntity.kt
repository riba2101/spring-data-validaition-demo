package app

import com.example.springdatavalidaitiondemo.entity.Entity
import com.example.springdatavalidaitiondemo.group.Save
import jakarta.validation.constraints.NotBlank
import org.jetbrains.exposed.dao.ULongEntity
import org.jetbrains.exposed.dao.ULongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.ULongIdTable
import org.jetbrains.exposed.sql.javatime.timestampWithTimeZone

internal object Dummies : ULongIdTable("dummy") {
    val name = varchar("name", 100)

    val createdAt = timestampWithTimeZone("created_at")
}

@Entity
class DummyEntity(id: EntityID<ULong>) : ULongEntity(id) {

    companion object : ULongEntityClass<DummyEntity>(Dummies)

    @get:NotBlank(groups = [Save::class])
    var name by Dummies.name
    var createdAt by Dummies.createdAt

}
