package by.ctefi.wordbox.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val original: String,
    val translation: String,
    val meaning: String = ""
)