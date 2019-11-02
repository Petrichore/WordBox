package by.ctefi.wordbox.view.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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
            confirmingWordId = bundle.getLong(DictionaryActivity.DEL_ID_WORD_KEY)
        }

        isCommitBtn = view.findViewById(R.id.delWordCommit)
        isCancelBtn = view.findViewById(R.id.delWordCancel)
    }

    override fun onResume() {
        super.onResume()
        val window = dialog?.window
        val width = resources.getDimensionPixelSize(R.dimen.confirmWordDelDialogWidth)
        val height = resources.getDimensionPixelSize(R.dimen.confirmWordDelDialogHeight)
        window?.setLayout(width, height)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isCommitBtn.setOnClickListener {
            if (confirmingWordId != -1L) {
                (activity as WordDeleteRouter).onDeleteConfirm(confirmingWordId)
            }
            dismiss()
        }

        isCancelBtn.setOnClickListener {
            dismiss()
        }
    }

    interface WordDeleteRouter {
        fun onDeleteConfirm(wordId: Long)
    }
}