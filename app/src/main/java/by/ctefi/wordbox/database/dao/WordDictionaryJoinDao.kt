package by.ctefi.wordbox.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.ctefi.wordbox.entity.Word
import by.ctefi.wordbox.entity.WordDictionaryJoin

@Dao
interface WordDictionaryJoinDao {

    @Query(
        "SELECT word.id, word.original, word.translation, word.meaning " +
                "FROM word_dictionary " +
                "INNER JOIN word ON word_dictionary.wordId = word.id " +
                "WHERE word_dictionary.dictionaryId = :dictionaryId " +
                "ORDER BY word.id DESC"
    )
    fun getWordsForDictionary(dictionaryId: Long): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWordForDictionary(wordDictionaryJoin: WordDictionaryJoin)
}