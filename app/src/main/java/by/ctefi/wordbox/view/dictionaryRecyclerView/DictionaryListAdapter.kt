package by.ctefi.wordbox.view.dictionaryRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Dictionary

class DictionaryListAdapter(
    private var dictionaryList: List<Dictionary>,
    private val listener: OnDictionaryElementClickListener
) : RecyclerView.Adapter<DictionaryListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryListViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dictionary, parent, false)

        val holder = DictionaryListViewHolder(item)

        holder.itemView.setOnClickListener {
            listener.onDictionaryClick(dictionaryList[holder.adapterPosition])
        }

        holder.itemView.findViewById<ImageView>(R.id.delDictionaryBtn).setOnClickListener {
            listener.showDeleteDictionaryDialog(dictionaryList[holder.adapterPosition].id)
        }
        return holder
    }

    override fun onBindViewHolder(holder: DictionaryListViewHolder, position: Int) {
        holder.bind(dictionaryList[position])
    }

    override fun getItemCount(): Int {
        return dictionaryList.size
    }

    fun updateDictionaryList(list: List<Dictionary>) {
        dictionaryList = list
        notifyDataSetChanged()
    }

    interface OnDictionaryElementClickListener {
        fun onDictionaryClick(dictionary: Dictionary)
        fun showDeleteDictionaryDialog(dictionaryId: Long)
    }
}