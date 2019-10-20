package by.ctefi.wordbox.viewModel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.ctefi.wordbox.viewModel.WordsForDictionaryViewModel

class WordsForDictionaryVMFactory(
    private val application: Application,
    private val dictionaryId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WordsForDictionaryViewModel(application,dictionaryId) as T
    }
}