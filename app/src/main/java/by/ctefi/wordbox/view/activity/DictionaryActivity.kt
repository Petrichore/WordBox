package by.ctefi.wordbox.view.activity

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Word
import by.ctefi.wordbox.view.wordRecyclerView.WordListAdapter
import by.ctefi.wordbox.viewModel.WordsForDictionaryViewModel
import by.ctefi.wordbox.viewModel.factory.WordsForDictionaryVMFactory

class DictionaryActivity : FragmentActivity(), WordListAdapter.OnWordClickListener {

    private val wordsForDictionaryVM by lazy {
        ViewModelProviders.of(
            this,
            WordsForDictionaryVMFactory(this.application, dictionaryId)
        ).get(WordsForDictionaryViewModel::class.java)
    }

    private var dictionaryId: Int = -1

    private var wordsList = emptyList<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        dictionaryId = intent.getIntExtra(DictionaryListActivity.CLICKED_DICTIONARY_ID, -1)

        val recyclerView: RecyclerView = findViewById(R.id.wordsList)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = WordListAdapter(wordsList, listener = this)

        wordsForDictionaryVM
            .wordsForDictionaryList
            .observe(this, Observer {
                if (it != null) {
                    (recyclerView.adapter as WordListAdapter).updateWordsList(it)
                } else {
                    //TODO show TextView Info "List is empty now. Add new word to begin learning!"
                }
            })

        val searchLine = findViewById<EditText>(R.id.searchLine)
        val addWordBtn = findViewById<ImageView>(R.id.addWordBtn)

        //TODO replace with dialog window for words adding

        addWordBtn.setOnClickListener {
            if (dictionaryId == 1) {
                wordsForDictionaryVM.insertWordForDictionary(
                    Word(1, "Name", "Имя, название"),
                    dictionaryId
                )
            } else if (dictionaryId == 2) {
                wordsForDictionaryVM.insertWordForDictionary(
                    Word(2, "Technologies", "Технологии"),
                    dictionaryId
                )
            }
        }
    }

    private fun onAddWordButtonClick() {
    }

    override fun onWordClick(wordId: Int) {
        //TODO show fragment with more detailed information
    }
}
