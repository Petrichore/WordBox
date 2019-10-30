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

    private lateinit var wordList: LiveData<List<Word>>

    fun getWordsForDictionary(dictionaryId: Long): LiveData<List<Word>> {
        if (!::wordList.isInitialized) {
            wordList = wordDictionaryJoinDao.getWordsForDictionary(dictionaryId)
        }
        return wordList
    }

    private fun getWordById(wordId: Long): Word {
        val list = wordList.value
        if (list != null) {
            for (word in list) {
                if (word.id == wordId) {
                    return word
                }
            }
        }
        // TODO replace with custom Exception
        throw Exception()
    }

    fun insertWord(word: Word, wordDictionaryJoin: WordDictionaryJoin) {
        val insertionThread = Thread(
            Runnable {
                wordDao.insertWord(word)
                wordDictionaryJoinDao.insertWordForDictionary(wordDictionaryJoin)
            })

        insertionThread.start()
    }

    fun deleteWord(wordId: Long) {
        val deleteThread = Thread(Runnable {
            val word = getWordById(wordId)
            wordDao.deleteWord(word)
        })
        deleteThread.start()
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
}