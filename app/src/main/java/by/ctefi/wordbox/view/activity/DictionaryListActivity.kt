package by.ctefi.wordbox.view.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Dictionary
import by.ctefi.wordbox.view.dictionaryRecyclerView.DictionaryListAdapter

class DictionaryListActivity : Activity(), DictionaryListAdapter.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary_list)

        val recyclerView: RecyclerView = findViewById(R.id.dictionaryList)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.isNestedScrollingEnabled = false


//        val wordsList = arrayListOf<Word>(
//            Word("имя", "name"),
//            Word("имя", "name"),
//            Word("имя", "name"),
//            Word("имя", "name"),
//            Word("имя", "name"),
//            Word("имя", "name"),
//            Word("имя", "name"),
//            Word("имя", "name"),
//            Word("имя", "name"),
//            Word("имя", "name"),
//            Word("имя", "name"),
//            Word("имя", "name")
//        )

        val dictionaryList = arrayListOf<Dictionary>(
            Dictionary("for work", "Professional English"),
            Dictionary("for education", "improve skills"),
            Dictionary("famous quotes", "expand words knowledge"),
            Dictionary("Countries and Capitals", "get to know the world"),
            Dictionary("IT english", "understand IT"),
            Dictionary("new terms", "professional English"),
            Dictionary("for work", "Professional English")
        )

        recyclerView.adapter = DictionaryListAdapter(dictionaryList, this)
    }

    override fun onDictionaryClickListener(dictionary: Dictionary) {

    }
}