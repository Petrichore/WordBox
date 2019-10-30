package by.ctefi.wordbox.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import by.ctefi.wordbox.R
import by.ctefi.wordbox.view.activity.DictionaryActivity

class ConfirmDelWordFragment : DialogFragment() {

    private lateinit var isCommitBtn: Button
    private lateinit var isCancelBtn: Button

    var confirmingWordId: Long = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_dialog_confirm_delete_word,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            confirmingWordId = bundle.getLong(DictionaryActivity.ID_WORD_KEY)
        }

        isCommitBtn = view.findViewById(R.id.delWordCommit)
        isCancelBtn = view.findViewById(R.id.delWordCancel)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isCommitBtn.setOnClickListener {
            if (confirmingWordId != -1L) {
                (activity as CommitWordDelete).onDeleteConfirm(confirmingWordId)
            }
            dismiss()
        }

        isCancelBtn.setOnClickListener {
            dismiss()
        }
    }

    interface CommitWordDelete {
        fun onDeleteConfirm(wordId: Long)
    }
}