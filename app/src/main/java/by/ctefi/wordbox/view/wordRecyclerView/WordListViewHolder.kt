package by.ctefi.wordbox.view.wordRecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.ctefi.wordbox.entity.Word
import kotlinx.android.synthetic.main.item_word.view.*

class WordListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(word: Word) {
        view.originalAndTranslation.text = word.original + " - " + word.translation
        view.wordMeaning.text = word.meaning
    }
}