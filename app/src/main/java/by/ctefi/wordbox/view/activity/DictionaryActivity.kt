package by.ctefi.wordbox.view.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Word
import by.ctefi.wordbox.view.wordRecyclerView.WordListAdapter

class DictionaryActivity : Activity(), WordListAdapter.OnWordClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        //TODO use to get selected dictionary from repository
        val dictionaryId = intent.getIntExtra(DictionaryListActivity.CLICKED_DICTIONARY_ID, -1)

        val recyclerView: RecyclerView = findViewById(R.id.wordsList)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.isNestedScrollingEnabled = false

        val wordsList = arrayListOf(
            Word(1, "name", "имя"),
            Word(2, "name", "имя"),
            Word(3, "name", "имя"),
            Word(4, "name", "имя"),
            Word(5, "name", "имя"),
            Word(5, "name", "имя"),
            Word(7, "name", "имя"),
            Word(8, "name", "имя"),
            Word(9, "name", "имя"),
            Word(10, "name", "имя"),
            Word(11, "name", "имя"),
            Word(12, "name", "имя")
        )

        recyclerView.adapter = WordListAdapter(wordsList, listener = this)
    }

    override fun onWordClick(wordId: Int) {
        //TODO add onClick response
    }
}
