package by.ctefi.wordbox.view.dictionaryRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Dictionary

class DictionaryListAdapter(
    private val dictionaryList: ArrayList<Dictionary>,
    private val listener: OnDictionaryClickListener
) : RecyclerView.Adapter<DictionaryListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryListViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_dictionary, parent, false)

        val holder = DictionaryListViewHolder(item)

        holder.itemView.setOnClickListener{
            listener.onDictionaryClick(dictionaryList[holder.adapterPosition])
        }

        return holder
    }

    override fun onBindViewHolder(holder: DictionaryListViewHolder, position: Int) {
        holder.bind(dictionaryList[position])
    }

    override fun getItemCount(): Int {
       return dictionaryList.size
    }

    interface OnDictionaryClickListener {
        fun onDictionaryClick(dictionary: Dictionary)
    }
}