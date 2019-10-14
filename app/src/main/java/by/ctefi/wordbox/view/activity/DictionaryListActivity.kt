package by.ctefi.wordbox.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Dictionary
import by.ctefi.wordbox.view.dictionaryRecyclerView.DictionaryListAdapter

class DictionaryListActivity : Activity(), DictionaryListAdapter.OnDictionaryClickListener {

    companion object {
        const val CLICKED_DICTIONARY_ID: String = "wertyui24412414"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary_list)

        val recyclerView: RecyclerView = findViewById(R.id.dictionaryList)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.isNestedScrollingEnabled = false

        //TODO observe DictionaryViewModel var
        // to get latest version of the dictionary list
        val dictionaryList = arrayListOf(
            Dictionary(1, "English"),
            Dictionary(2, "Education"),
            Dictionary(3, "Quotes"),
            Dictionary(4, "Countries and Capitals"),
            Dictionary(5, "IT english"),
            Dictionary(6, "new terms"),
            Dictionary(7, "for work")
        )

        recyclerView.adapter = DictionaryListAdapter(dictionaryList, this)
    }

    override fun onDictionaryClick(dictionary: Dictionary) {
        val intent = Intent(this, DictionaryActivity::class.java)
        intent.putExtra(CLICKED_DICTIONARY_ID, dictionary.id)
        startActivity(intent)
    }
}