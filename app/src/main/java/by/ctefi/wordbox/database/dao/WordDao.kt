package by.ctefi.wordbox.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.ctefi.wordbox.entity.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM word ORDER BY id DESC")
    fun getAllWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: Word)
}