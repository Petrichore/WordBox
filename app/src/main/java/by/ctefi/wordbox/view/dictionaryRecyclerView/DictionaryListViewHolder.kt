package by.ctefi.wordbox.view.dictionaryRecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.ctefi.wordbox.entity.Dictionary
import kotlinx.android.synthetic.main.item_library.view.*

class DictionaryListViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {

    fun bind(dictionary: Dictionary){
        item.dictionaryName.text = dictionary.name
        item.dictionaryShortNote.text = dictionary.description
        item.dictionaryWordsAmount.text = dictionary.wordsAmount.toString()
    }
}