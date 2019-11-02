package by.ctefi.wordbox.view.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Word
import by.ctefi.wordbox.view.activity.DictionaryActivity

class EditWordInfoFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.fragment_dialog_edit_word_info, container, false)
    }

    private lateinit var word: Word
    private lateinit var editWordOriginal: TextView
    private lateinit var editWordTranslation: TextView
    private lateinit var editMeaningField: TextView
    private lateinit var commitEditingBtn: Button
    private lateinit var cancelEditingBtn: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            word = bundle.getSerializable(DictionaryActivity.INFO_ID_WORD_KEY) as Word
        }

        editWordOriginal = view.findViewById(R.id.editWordOriginal)
        editWordTranslation = view.findViewById(R.id.editWordTranslation)
        editMeaningField = view.findViewById(R.id.editWordMeaning)
        commitEditingBtn = view.findViewById(R.id.editWordCommitBtn)
        cancelEditingBtn = view.findViewById(R.id.editWordCancelBtn)

        cancelEditingBtn.setOnClickListener {
            dismiss()
        }

        if (::word.isInitialized) {
            val meaning = word.meaning
            val original = word.original
            val translation = word.translation

            editWordOriginal.text = original
            editWordTranslation.text = translation
            editMeaningField.text = meaning
        } else {
            dismiss()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        commitEditingBtn.setOnClickListener {
            (activity as EditWordRouter).onWordEdited(
                word.id,
                editWordOriginal.text.toString(),
                editWordTranslation.text.toString(),
                editMeaningField.text.toString()
            )
            dismiss()
        }
    }

    interface EditWordRouter {
        fun onWordEdited(id: Long, original: String, translation: String, meaning: String)
    }
}