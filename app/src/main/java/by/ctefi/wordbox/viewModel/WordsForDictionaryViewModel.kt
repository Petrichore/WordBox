package by.ctefi.wordbox.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.ctefi.wordbox.database.WordBoxDatabase
import by.ctefi.wordbox.entity.Word
import by.ctefi.wordbox.model.WordsRepository

class WordsForDictionaryViewModel(application: Application) : AndroidViewModel(application) {

    private val wordsForDictionaryList: MutableLiveData<ArrayList<Word>> by lazy {
        MutableLiveData<ArrayList<Word>>()
    }

    private val wordsRepository: WordsRepository

    init {
        val wordBoxDatabase = WordBoxDatabase.getDatabase(application)
        wordsRepository =
            WordsRepository(
                wordDictionaryJoinDao = wordBoxDatabase.getWordDictionaryJoinDao(),
                wordDao = wordBoxDatabase.getWordDao()
            )
    }

    fun updateWordsForDictionaryList(dictionaryId: Int){
        wordsForDictionaryList.value = wordsRepository.getWordsForDictionary(dictionaryId).value
    }
}