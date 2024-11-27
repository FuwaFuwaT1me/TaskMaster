package fuwafuwa.time.core_data.entity.app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
