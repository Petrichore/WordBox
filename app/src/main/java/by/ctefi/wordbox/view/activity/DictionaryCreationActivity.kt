package by.ctefi.wordbox.view.activity

import android.app.Activity
import android.os.Bundle
import by.ctefi.wordbox.R

//TODO should be replaced with a fragment in dictionaryList
class DictionaryCreationActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_dictionary)
    }
}