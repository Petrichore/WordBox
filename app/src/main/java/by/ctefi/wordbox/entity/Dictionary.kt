package by.ctefi.wordbox.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class Dictionary(
    @PrimaryKey
    val id: Long,
    val name: String,
    var wordsAmount: Int = 0,
    @Ignore
    val wordsList: ArrayList<Word>?
) {
    constructor(id: Long, name: String, wordsAmount: Int = 0)
            : this(id, name, wordsAmount, null)
}
