package by.ctefi.wordbox.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "word_dictionary",
    primaryKeys = ["wordId", "dictionaryId"],
    foreignKeys = [
        ForeignKey(
            entity = Word::class,
            parentColumns = ["id"],
            childColumns = ["wordId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Dictionary::class,
            parentColumns = ["id"],
            childColumns = ["dictionaryId"],
            onDelete = CASCADE
        )
    ]
)
data class WordDictionaryJoin(

    val wordId: Long,
    @ColumnInfo(index = true)
    val dictionaryId: Long
)