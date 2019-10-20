package by.ctefi.wordbox.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import by.ctefi.wordbox.database.WordBoxDatabase
import by.ctefi.wordbox.entity.Dictionary
import by.ctefi.wordbox.model.DictionariesRepository

//TODO USE view model factory
class DictionaryViewModel(application: Application) : AndroidViewModel(application) {

    val dictionaryList: LiveData<List<Dictionary>>

    private val dictionaryRepository: DictionariesRepository = DictionariesRepository(
        WordBoxDatabase.getDatabase(application).getDictionaryDao()
    )

    init {
        dictionaryList = dictionaryRepository.getAllDictionaries()
    }

    fun insertDictionary(dictionary: Dictionary) {
        dictionaryRepository.insertDictionary(dictionary)
    }
}