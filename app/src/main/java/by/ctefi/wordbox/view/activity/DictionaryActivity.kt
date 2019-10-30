package by.ctefi.wordbox.view.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Word
import by.ctefi.wordbox.view.fragment.AddWordFragment
import by.ctefi.wordbox.view.fragment.ConfirmDelWordFragment
import by.ctefi.wordbox.view.wordRecyclerView.WordListAdapter
import by.ctefi.wordbox.viewModel.WordsForDictionaryViewModel
import by.ctefi.wordbox.viewModel.factory.WordsForDictionaryVMFactory

class DictionaryActivity : FragmentActivity(), WordListAdapter.OnWordClickListener,
    AddWordFragment.CommitDialog, ConfirmDelWordFragment.CommitWordDelete {

    companion object {
        const val ID_WORD_KEY = "34567ujhygtfrdsxdcfg"
    }

    private val wordsForDictionaryVM by lazy {
        ViewModelProviders.of(
            this,
            WordsForDictionaryVMFactory(this.application, dictionaryId)
        ).get(WordsForDictionaryViewModel::class.java)
    }

    private var dictionaryId: Long = -1

    private var wordsList = emptyList<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        dictionaryId = intent.getLongExtra(DictionaryListActivity.ID_DICTIONARY_KEY, -1)

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
                    // TODO show TextView Info "List is empty now. Add new word to begin learning!"
                }
            })

        findViewById<TextView>(R.id.name).text = wordsForDictionaryVM.getDictionaryName()

        // TODO filter word list by entered word name (original)
        // val searchLine = findViewById<EditText>(R.id.searchLine)
        val addWordBtn = findViewById<ImageView>(R.id.addWordBtn)

        addWordBtn.setOnClickListener {
            openAddWordDialog(supportFragmentManager)
        }
    }

    private fun openAddWordDialog(fragmentManager: FragmentManager) {
        val dialogFragment = AddWordFragment()
        dialogFragment.show(fragmentManager, "addWordDialog")
    }

    override fun onWordCreated(word: Word) {
        wordsForDictionaryVM.insertWordForDictionary(word, dictionaryId)
    }

    override fun onWordClick(wordId: Long) {
        // TODO show dialog fragment with more detailed information
    }

    override fun showDelWordDialog(wordId: Long) {
        val confirmDelWordFragment = ConfirmDelWordFragment()
        val bundle = Bundle()

        bundle.putLong(ID_WORD_KEY, wordId)
        confirmDelWordFragment.arguments = bundle

        confirmDelWordFragment.show(supportFragmentManager, "showDelWordFragment")
    }

    override fun onDeleteConfirm(wordId: Long) {
        wordsForDictionaryVM.deleteWordForDictionary(wordId)
    }
}