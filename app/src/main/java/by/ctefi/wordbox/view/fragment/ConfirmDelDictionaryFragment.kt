package by.ctefi.wordbox.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import by.ctefi.wordbox.R
import by.ctefi.wordbox.view.activity.DictionaryListActivity

class ConfirmDelDictionaryFragment : DialogFragment() {

    private lateinit var isCommitBtn: Button
    private lateinit var isCancelBtn: Button

    var confirmingDictionaryId: Long = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_dialog_confirm_delete_dictionary,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            confirmingDictionaryId = bundle.getLong(DictionaryListActivity.ID_DICTIONARY_KEY)
        }

        isCommitBtn = view.findViewById(R.id.delDictionaryCommit)
        isCancelBtn = view.findViewById(R.id.delDictionaryCancel)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isCommitBtn.setOnClickListener {
            if (confirmingDictionaryId != -1L) {
                (activity as CommitDictionaryDelete).onDeleteConfirm(confirmingDictionaryId)
            }
            dismiss()
        }

        isCancelBtn.setOnClickListener {
            dismiss()
        }
    }

    interface CommitDictionaryDelete {
        fun onDeleteConfirm(dictionaryId: Long)
    }
}