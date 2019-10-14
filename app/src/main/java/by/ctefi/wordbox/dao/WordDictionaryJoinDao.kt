package by.ctefi.wordbox.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import by.ctefi.wordbox.entity.Word

@Dao
interface WordDictionaryJoinDao {

    @Query(
        "SELECT word.id, word.original, word.translation, word.meaning " +
                    "FROM word_dictionary " +
                    "INNER JOIN word ON word_dictionary.wordId = word.id " +
                "WHERE word_dictionary.dictionaryId = :dictionaryId " +
                "ORDER BY word.id DESC"
    )
    fun getWordsForDictionary(dictionaryId: Int): MutableLiveData<ArrayList<Word>>
}