package by.ctefi.wordbox.view.wordRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Word

class WordListAdapter(
    private val wordList: ArrayList<Word>,
    val listener: OnWordClickListener
) : RecyclerView.Adapter<WordListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)

        val viewHolder = WordListViewHolder(item)

        viewHolder.itemView.setOnClickListener{
            listener.onWordClick(wordList[viewHolder.adapterPosition].id)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    interface OnWordClickListener {
        fun onWordClick(wordId: Int)
    }
}