package by.ctefi.wordbox.view.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Word
import by.ctefi.wordbox.view.activity.DictionaryActivity

class WordInfoFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.fragment_dialog_word_info, container, false)
    }

    private lateinit var word: Word
    private lateinit var wordField: TextView
    private lateinit var meaningField: TextView
    private lateinit var meaningMarker: TextView
    private lateinit var editInfoBtn: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            word = bundle.getSerializable(DictionaryActivity.INFO_ID_WORD_KEY) as Word
        }

        wordField = view.findViewById(R.id.infoOriginalAndTranslation)
        meaningField = view.findViewById(R.id.infoMeaning)
        meaningMarker = view.findViewById(R.id.infoMeaningMarker)
        editInfoBtn = view.findViewById(R.id.infoEdit)

        if (::word.isInitialized) {
            val meaning = word.meaning
            val original = word.original
            val translation = word.translation

            wordField.text = "$original - $translation"
            meaningMarker.visibility = View.VISIBLE

            if (!meaning.isBlank()) {
                meaningField.text = meaning
            } else {
                meaningMarker.visibility = View.GONE
                meaningField.textSize = 15f
                meaningField.gravity = Gravity.CENTER
                meaningField.text =
                    "You can add description to use this word in your life confidently!"
            }
        } else {
            dismiss()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        editInfoBtn.setOnClickListener {
            (activity as WordInfoRouter).onEditClicked(word)
            dismiss()
        }
    }

    interface WordInfoRouter {
        fun onEditClicked(word: Word)
    }
}