package by.ctefi.wordbox.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.ctefi.wordbox.entity.Dictionary

@Dao
interface DictionaryDao {

    @Query("SELECT * FROM dictionary ORDER BY id DESC")
    fun getAllDictionary(): LiveData<List<Dictionary>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDictionary(dictionary: Dictionary)
}