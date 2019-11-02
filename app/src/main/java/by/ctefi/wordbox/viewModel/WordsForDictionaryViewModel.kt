package by.ctefi.wordbox.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import by.ctefi.wordbox.database.WordBoxDatabase
import by.ctefi.wordbox.entity.Word
import by.ctefi.wordbox.entity.WordDictionaryJoin
import by.ctefi.wordbox.model.DictionariesRepository
import by.ctefi.wordbox.model.WordsRepository

class WordsForDictionaryViewModel(
    application: Application,
    dictionaryId: Long
) : AndroidViewModel(application) {

    val wordsForDictionaryList: LiveData<List<Word>>
    private val currentDictionaryId: Long = dictionaryId

    private val wordsRepository: WordsRepository
    private val dictionariesRepository: DictionariesRepository

    init {
        val wordBoxDatabase = WordBoxDatabase.getDatabase(application)
        wordsRepository = WordsRepository(
            wordBoxDatabase.getWordDictionaryJoinDao(),
            wordBoxDatabase.getWordDao()
        )
        dictionariesRepository = DictionariesRepository.getInstance(application)

        wordsForDictionaryList = wordsRepository.getWordsForDictionary(dictionaryId)
    }

    fun insertWordForDictionary(
        id: Long,
        original: String,
        translation: String,
        meaning: String
    ) {
        if (validateWord(original, translation)) {
            wordsRepository.insertWord(
                Word(id, original, translation, meaning),
                WordDictionaryJoin(id, currentDictionaryId)
            )
        }
    }

    fun getDictionaryName(): String {
        return dictionariesRepository.getDictionaryById(currentDictionaryId).name
    }

    fun getWordsForDictionaryList(): List<Word>? {
        return wordsForDictionaryList.value
    }

    fun deleteWordForDictionary(wordId: Long) {
        wordsRepository.deleteWord(wordId)
    }

    fun updateDictionaryWordList(wordList: List<Word>) {
        dictionariesRepository.updateDictionaryWordList(currentDictionaryId, wordList)
    }

    private fun validateWord(
        original: String,
        translation: String
    ): Boolean {
        return original.isNotBlank() && translation.isNotBlank()
    }
}