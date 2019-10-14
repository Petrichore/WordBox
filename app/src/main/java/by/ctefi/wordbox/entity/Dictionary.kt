package by.ctefi.wordbox.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class Dictionary(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val wordsAmount: Int = 0,
    val wordsList: ArrayList<Word>? = null
)
