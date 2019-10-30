package by.ctefi.wordbox.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import by.ctefi.wordbox.database.WordBoxDatabase
import by.ctefi.wordbox.entity.Dictionary
import by.ctefi.wordbox.entity.Word
import by.ctefi.wordbox.entity.WordDictionaryJoin
import by.ctefi.wordbox.model.DictionariesRepository
import by.ctefi.wordbox.model.WordsRepository

class WordsForDictionaryViewModel(
    application: Application,
    dictionaryId: Long
) : AndroidViewModel(application) {

    val wordsForDictionaryList: LiveData<List<Word>>
    private val currentDictionary: Dictionary

    private val wordsRepository: WordsRepository

    init {
        val wordBoxDatabase = WordBoxDatabase.getDatabase(application)
        wordsRepository = WordsRepository(
            wordBoxDatabase.getWordDictionaryJoinDao(),
            wordBoxDatabase.getWordDao()
        )

        wordsForDictionaryList = wordsRepository.getWordsForDictionary(dictionaryId)
        currentDictionary =
            DictionariesRepository.getInstance(application).getDictionaryById(dictionaryId)
    }

    fun insertWordForDictionary(word: Word, dictionaryId: Long) {
        wordsRepository.insertWord(word, WordDictionaryJoin(word.id, dictionaryId))
    }

    fun getDictionaryName(): String {
        return currentDictionary.name
    }

    fun deleteWordForDictionary(wordId: Long) {
        wordsRepository.deleteWord(wordId)
    }
}