package by.ctefi.wordbox.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Dictionary
import by.ctefi.wordbox.view.dictionaryRecyclerView.DictionaryListAdapter
import by.ctefi.wordbox.viewModel.DictionaryViewModel

class DictionaryListActivity : FragmentActivity(), DictionaryListAdapter.OnDictionaryClickListener {

    companion object {
        const val CLICKED_DICTIONARY_ID: String = "wertyui24412414"
    }

    private lateinit var dictionaryViewModel: DictionaryViewModel
    private var dictionaryList: List<Dictionary> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary_list)

        val recyclerView: RecyclerView = findViewById(R.id.dictionaryList)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = DictionaryListAdapter(dictionaryList, this)

        dictionaryViewModel = ViewModelProviders.of(this).get(DictionaryViewModel::class.java)

        dictionaryViewModel
            .dictionaryList
            .observe(this, Observer {
                if (it != null) {
                    (recyclerView.adapter as DictionaryListAdapter).updateDictionaryList(it)
                } else {
                    Log.d("AAAA", "dictionaryList is Null")
                }
            })
    }

    override fun onDictionaryClick(dictionary: Dictionary) {
        val intent = Intent(this, DictionaryActivity::class.java)
        intent.putExtra(CLICKED_DICTIONARY_ID, dictionary.id)
        startActivity(intent)
    }
}