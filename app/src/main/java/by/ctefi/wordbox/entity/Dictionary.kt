package by.ctefi.wordbox.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class Dictionary(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String = "",
    var wordsAmount: Int = 0,
    @Ignore
    var wordsList: List<Word>?
) {
    constructor(id: Long, name: String, description: String, wordsAmount: Int = 0)
            : this(id, name, description, wordsAmount, null)
}
