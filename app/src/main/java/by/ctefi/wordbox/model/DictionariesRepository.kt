package by.ctefi.wordbox.model

import android.util.Log
import androidx.lifecycle.LiveData
import by.ctefi.wordbox.database.dao.DictionaryDao
import by.ctefi.wordbox.entity.Dictionary

class DictionariesRepository(private val dictionaryDao: DictionaryDao) {

    fun getAllDictionaries(): LiveData<List<Dictionary>> {
        return dictionaryDao.getAllDictionary()
    }

    fun insertDictionary(dictionary: Dictionary) {
        val insertionThread = Thread(
            Runnable {
                Log.d("AAAA", "INSERTION RUN")
                dictionaryDao.insertDictionary(dictionary)
            })
        Log.d("AAAA", "METHOD RUN")
        insertionThread.start()
    }
}
