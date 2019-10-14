package by.ctefi.wordbox.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "word_dictionary",
    primaryKeys = ["id", "id"],
    foreignKeys = [
        ForeignKey(
            entity = Word::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        ),
        ForeignKey(
            entity = Dictionary::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class WordDictionaryJoin(
    val wordId: Int,
    val dictionaryId: Int
)