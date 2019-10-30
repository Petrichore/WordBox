package by.ctefi.wordbox.view.wordRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Word

class WordListAdapter(
    private var wordList: List<Word>,
    val listener: OnWordClickListener
) : RecyclerView.Adapter<WordListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)

        val viewHolder = WordListViewHolder(item)

        viewHolder.itemView.setOnClickListener {
            listener.onWordClick(wordList[viewHolder.adapterPosition].id)
        }

        viewHolder.itemView.findViewById<ImageView>(R.id.deleteWordBtn).setOnClickListener {
            listener.showDelWordDialog(wordList[viewHolder.adapterPosition].id)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    fun updateWordsList(list: List<Word>) {
        wordList = list
        notifyDataSetChanged()
    }

    interface OnWordClickListener {
        fun onWordClick(wordId: Long)
        fun showDelWordDialog(wordId: Long)
    }
}