package by.ctefi.wordbox.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.ctefi.wordbox.database.dao.DictionaryDao
import by.ctefi.wordbox.database.dao.WordDao
import by.ctefi.wordbox.database.dao.WordDictionaryJoinDao
import by.ctefi.wordbox.entity.Dictionary
import by.ctefi.wordbox.entity.Word
import by.ctefi.wordbox.entity.WordDictionaryJoin

@Database(
    entities = [Word::class, Dictionary::class, WordDictionaryJoin::class],
    version = 2, exportSchema = false
)
abstract class WordBoxDatabase : RoomDatabase() {

    abstract fun getWordDao(): WordDao
    abstract fun getDictionaryDao(): DictionaryDao
    abstract fun getWordDictionaryJoinDao(): WordDictionaryJoinDao

    companion object {
        @Volatile
        private var INSTANCE: WordBoxDatabase? = null

        fun getDatabase(context: Context): WordBoxDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            WordBoxDatabase::class.java,
                            "word_box"
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}