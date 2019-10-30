package by.ctefi.wordbox.model

import android.app.Application
import androidx.lifecycle.LiveData
import by.ctefi.wordbox.database.WordBoxDatabase
import by.ctefi.wordbox.database.dao.DictionaryDao
import by.ctefi.wordbox.entity.Dictionary

class DictionariesRepository private constructor(private val dictionaryDao: DictionaryDao) {

    companion object {
        private var INSTANCE: DictionariesRepository? = null

        fun getInstance(application: Application): DictionariesRepository {
            if (INSTANCE == null) {
                val wordBoxDatabase = WordBoxDatabase.getDatabase(application)
                INSTANCE = DictionariesRepository(wordBoxDatabase.getDictionaryDao())
            }
            return INSTANCE!!
        }
    }

    private lateinit var dictionaryList: LiveData<List<Dictionary>>

    fun getAllDictionaries(): LiveData<List<Dictionary>> {
        if (!::dictionaryList.isInitialized) {
            dictionaryList = dictionaryDao.getAllDictionary()
        }
        return dictionaryList
    }

    fun getDictionaryById(dictionaryId: Long): Dictionary {
        val list = dictionaryList.value
        if (list != null) {
            for (dictionary in list) {
                if (dictionary.id == dictionaryId) {
                    return dictionary
                }
            }
        }
        throw Exception()
    }

    fun insertDictionary(dictionary: Dictionary) {
        val insertionThread = Thread(
            Runnable {
                dictionaryDao.insertDictionary(dictionary)
            })
        insertionThread.start()
    }

    fun deleteDictionary(dictionaryId: Long) {
        val deleteThread = Thread(
            Runnable {
                val dictionary = getDictionaryById(dictionaryId)
                dictionaryDao.deleteDictionary(dictionary)
            })
        deleteThread.start()
    }
}