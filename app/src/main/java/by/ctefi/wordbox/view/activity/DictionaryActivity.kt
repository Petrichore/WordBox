package by.ctefi.wordbox.view.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
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
import by.ctefi.wordbox.view.fragment.EditWordInfoFragment
import by.ctefi.wordbox.view.fragment.WordInfoFragment
import by.ctefi.wordbox.view.wordRecyclerView.WordListAdapter
import by.ctefi.wordbox.viewModel.WordsForDictionaryViewModel
import by.ctefi.wordbox.viewModel.factory.WordsForDictionaryVMFactory

class DictionaryActivity : FragmentActivity(), WordListAdapter.OnWordClickListener,
    AddWordFragment.AddWordRouter, ConfirmDelWordFragment.WordDeleteRouter,
    WordInfoFragment.WordInfoRouter, EditWordInfoFragment.EditWordRouter {

    companion object {
        const val DEL_ID_WORD_KEY = "34567ujhygtfrdsxdcfg"
        const val INFO_ID_WORD_KEY = "aywtjiodawdawdawdawdg"
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

        val helloMsg = findViewById<TextView>(R.id.helloMsgDictionary)
        val addWordBtn = findViewById<ImageView>(R.id.addWordBtn)

        dictionaryId = intent.getLongExtra(DictionaryListActivity.ID_DICTIONARY_KEY, -1)

        val recyclerView: RecyclerView = findViewById(R.id.wordsList)
        recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = WordListAdapter(wordsList, listener = this)

        wordsForDictionaryVM
            .wordsForDictionaryList
            .observe(this, Observer {
                if (it.isNotEmpty()) {
                    helloMsg.visibility = View.GONE
                } else {
                    helloMsg.visibility = View.VISIBLE
                }
                (recyclerView.adapter as WordListAdapter).updateWordsList(it)
                wordsForDictionaryVM.updateDictionaryWordList(it)
            })
        findViewById<TextView>(R.id.name).text = wordsForDictionaryVM.getDictionaryName()

        findViewById<EditText>(R.id.searchLine).addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(substring: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (substring != null) {
                    (recyclerView.adapter as WordListAdapter).updateWordsList(
                        wordsForDictionaryVM.getWordsForDictionaryList()?.filter {
                            it.original.contains(substring, true)
                        } as List<Word>)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        addWordBtn.setOnClickListener {
            openAddWordDialog(supportFragmentManager)
        }
    }

    private fun openAddWordDialog(fragmentManager: FragmentManager) {
        val dialogFragment = AddWordFragment()
        dialogFragment.show(fragmentManager, "addWordDialog")
    }

    override fun onWordCreated(id: Long, original: String, translation: String, meaning: String) {
        wordsForDictionaryVM.insertWordForDictionary(id, original, translation, meaning)
    }

    override fun onWordClick(word: Word) {
        val wordInfoFragment = WordInfoFragment()
        val bundle = Bundle()

        bundle.putSerializable(INFO_ID_WORD_KEY, word)
        wordInfoFragment.arguments = bundle

        wordInfoFragment.show(supportFragmentManager, "wordInfo")
    }

    override fun showDelWordDialog(wordId: Long) {
        val confirmDelWordFragment = ConfirmDelWordFragment()
        val bundle = Bundle()

        bundle.putLong(DEL_ID_WORD_KEY, wordId)
        confirmDelWordFragment.arguments = bundle

        confirmDelWordFragment.show(supportFragmentManager, "showDelWordFragment")
    }

    override fun onDeleteConfirm(wordId: Long) {
        wordsForDictionaryVM.deleteWordForDictionary(wordId)
    }

    override fun onEditClicked(word: Word) {
        val editWordInfoFragment = EditWordInfoFragment()

        val bundle = Bundle()
        bundle.putSerializable(INFO_ID_WORD_KEY, word)
        editWordInfoFragment.arguments = bundle

        editWordInfoFragment.show(supportFragmentManager, "editWordInfo")
    }

    override fun onWordEdited(id: Long, original: String, translation: String, meaning: String) {
        wordsForDictionaryVM.insertWordForDictionary(id, original, translation, meaning)
    }
}