package by.ctefi.wordbox.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Dictionary
import by.ctefi.wordbox.view.dictionaryRecyclerView.DictionaryListAdapter
import by.ctefi.wordbox.view.fragment.AddDictionaryFragment
import by.ctefi.wordbox.view.fragment.ConfirmDelDictionaryFragment
import by.ctefi.wordbox.viewModel.DictionaryViewModel

class DictionaryListActivity : FragmentActivity(),
    DictionaryListAdapter.OnDictionaryElementClickListener,
    AddDictionaryFragment.DictionaryCreationRouter,
    ConfirmDelDictionaryFragment.CommitDictionaryDelete {

    companion object {
        const val ID_DICTIONARY_KEY: String = "wertyui24412414"
    }

    private val dictionaryViewModel: DictionaryViewModel by lazy {
        ViewModelProviders.of(this).get(DictionaryViewModel::class.java)
    }

    private var dictionaryList: List<Dictionary> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary_list)

        val helloMsg = findViewById<TextView>(R.id.helloMsgDictionaryList)
        val recyclerView: RecyclerView = findViewById(R.id.dictionaryList)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = DictionaryListAdapter(dictionaryList, this)

        dictionaryViewModel
            .dictionaryList
            .observe(this, Observer {
                if (it.isNotEmpty()) {
                    helloMsg.visibility = View.GONE
                } else {
                    helloMsg.visibility = View.VISIBLE
                }
                (recyclerView.adapter as DictionaryListAdapter).updateDictionaryList(it)
            })

        findViewById<ImageView>(R.id.newDictionary).setOnClickListener {
            openCreateDictionaryDialog(supportFragmentManager)
        }
    }

    override fun onDictionaryClick(dictionary: Dictionary) {
        val intent = Intent(this, DictionaryActivity::class.java)
        intent.putExtra(ID_DICTIONARY_KEY, dictionary.id)
        startActivity(intent)
    }

    override fun showDeleteDictionaryDialog(dictionaryId: Long) {
        val confirmDeleteDialogFragment = ConfirmDelDictionaryFragment()
        val bundle = Bundle()
        bundle.putLong(ID_DICTIONARY_KEY, dictionaryId)
        confirmDeleteDialogFragment.arguments = bundle
        confirmDeleteDialogFragment.show(supportFragmentManager, "confirmDictionaryDel")
    }

    override fun onDictionaryCreated(id: Long, name: String, description: String) {
        dictionaryViewModel.insertDictionary(id, name, description)
    }

    override fun onDeleteConfirm(dictionaryId: Long) {
        dictionaryViewModel.deleteDictionary(dictionaryId)
    }

    private fun openCreateDictionaryDialog(fragmentManager: FragmentManager) {
        val dialogFragment = AddDictionaryFragment()
        dialogFragment.show(fragmentManager, "addWordDialog")
    }
}