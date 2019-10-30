package by.ctefi.wordbox.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val original: String,
    val translation: String,
    val meaning: String = ""
)