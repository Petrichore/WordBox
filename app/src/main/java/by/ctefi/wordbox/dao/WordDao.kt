package by.ctefi.wordbox.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import by.ctefi.wordbox.entity.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM word ORDER BY id DESC")
    fun getAllDictionary(): MutableLiveData<ArrayList<Word>>


}