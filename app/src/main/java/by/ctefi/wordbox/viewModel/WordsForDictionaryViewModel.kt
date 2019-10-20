package by.ctefi.wordbox.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import by.ctefi.wordbox.database.WordBoxDatabase
import by.ctefi.wordbox.entity.Word
import by.ctefi.wordbox.entity.WordDictionaryJoin
import by.ctefi.wordbox.model.WordsRepository

class WordsForDictionaryViewModel(
    application: Application,
    dictionaryId: Int
) : AndroidViewModel(application) {

    val wordsForDictionaryList: LiveData<List<Word>>

    private val wordsRepository: WordsRepository

    init {
        val wordBoxDatabase = WordBoxDatabase.getDatabase(application)
        wordsRepository =
            WordsRepository(
                wordDictionaryJoinDao = wordBoxDatabase.getWordDictionaryJoinDao(),
                wordDao = wordBoxDatabase.getWordDao()
            )
        wordsForDictionaryList = wordsRepository.getWordsForDictionary(dictionaryId)
    }

    fun insertWordForDictionary(word: Word, dictionaryId: Int) {
        wordsRepository.insertWord(word, WordDictionaryJoin(word.id, dictionaryId))
    }
}