package by.ctefi.wordbox.model

import androidx.lifecycle.LiveData
import by.ctefi.wordbox.database.dao.WordDao
import by.ctefi.wordbox.database.dao.WordDictionaryJoinDao
import by.ctefi.wordbox.entity.Word
import by.ctefi.wordbox.entity.WordDictionaryJoin

class WordsRepository(
    private val wordDictionaryJoinDao: WordDictionaryJoinDao,
    private val wordDao: WordDao
) {

    fun getWordsForDictionary(dictionaryId: Int): LiveData<List<Word>> {
        return wordDictionaryJoinDao.getWordsForDictionary(dictionaryId)
    }

    fun getAllwords(): LiveData<List<Word>> {
        return wordDao.getAllWords()
    }

    fun insertWord(word: Word) {
        val insertionThread = Thread(
            Runnable {
                wordDao.insertWord(word)
            })

        insertionThread.start()
    }

    fun insertWord(word: Word, wordDictionaryJoin: WordDictionaryJoin) {
        val insertionThread = Thread(
            Runnable {
                wordDao.insertWord(word)
                wordDictionaryJoinDao.insertWordForDictionary(wordDictionaryJoin)
            })

        insertionThread.start()
    }
}