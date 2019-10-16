package by.ctefi.wordbox.model

import androidx.lifecycle.LiveData
import by.ctefi.wordbox.database.dao.WordDao
import by.ctefi.wordbox.database.dao.WordDictionaryJoinDao
import by.ctefi.wordbox.entity.Word

class WordsRepository(private val wordDictionaryJoinDao: WordDictionaryJoinDao, private val wordDao: WordDao) {

    fun getWordsForDictionary(dictionaryId: Int): LiveData<ArrayList<Word>> {
        return wordDictionaryJoinDao.getWordsForDictionary(dictionaryId)
    }

    fun insertWordForDictionary(wordId: Int, dictionaryId: Int) {
        wordDictionaryJoinDao.insertWordForDictionary(wordId, dictionaryId)
    }

    fun getAllwords(): LiveData<ArrayList<Word>>{
        return wordDao.getAllWords()
    }

    fun insertWord(word: Word){
        wordDao.insertWord(word)
    }
}