package by.ctefi.wordbox.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import by.ctefi.wordbox.entity.Dictionary
import by.ctefi.wordbox.model.DictionariesRepository

class DictionaryViewModel(application: Application) : AndroidViewModel(application) {

    val dictionaryList: LiveData<List<Dictionary>>

    private val dictionaryRepository: DictionariesRepository =
        DictionariesRepository.getInstance(application)

    init {
        dictionaryList = dictionaryRepository.getAllDictionaries()
    }

    fun insertDictionary(id: Long, dictionaryName: String, description: String) {
        if (validateInput(dictionaryName)) {
            dictionaryRepository.insertDictionary(Dictionary(id, dictionaryName, description))
        }
    }

    fun deleteDictionary(dictionaryId: Long) {
        dictionaryRepository.deleteDictionary(dictionaryId)
    }

    private fun validateInput(dictionaryName: String): Boolean {
        return dictionaryName.isNotBlank()
    }
}