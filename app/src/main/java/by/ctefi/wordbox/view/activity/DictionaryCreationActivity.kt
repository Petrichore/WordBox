package by.ctefi.wordbox.view.activity

import android.app.Activity
import android.os.Bundle
import by.ctefi.wordbox.R
import by.ctefi.wordbox.database.WordBoxDatabase
import by.ctefi.wordbox.entity.Dictionary
import by.ctefi.wordbox.model.DictionariesRepository

//TODO should be replaced with a fragment in dictionaryList
class DictionaryCreationActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_dictionary)

        val wordBoxDb = WordBoxDatabase.getDatabase(this)
        val dictionariesRepo = DictionariesRepository(wordBoxDb.getDictionaryDao())
        dictionariesRepo.insertDictionary(Dictionary(1,"General English"))
        dictionariesRepo.insertDictionary(Dictionary(2,"IT English"))
    }
}