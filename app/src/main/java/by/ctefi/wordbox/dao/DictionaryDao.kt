package by.ctefi.wordbox.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import by.ctefi.wordbox.entity.Dictionary

@Dao
interface DictionaryDao {

    @Query("SELECT * FROM dictionary ORDER BY id DESC")
    fun getAllDictionary(): MutableLiveData<ArrayList<Dictionary>>

}