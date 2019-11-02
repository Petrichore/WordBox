package by.ctefi.wordbox.view.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import by.ctefi.wordbox.R
import by.ctefi.wordbox.view.activity.DictionaryActivity

class AddWordFragment : DialogFragment() {

    private lateinit var originalField: EditText
    private lateinit var translationField: EditText
    private lateinit var meaningField: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.fragment_dialog_add_word, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        originalField = view.findViewById(R.id.addWordOriginal)
        translationField = view.findViewById(R.id.addWordTranslation)
        meaningField = view.findViewById(R.id.addWordMeaning)

        view.findViewById<Button>(R.id.addWordCancel).setOnClickListener {
            dismiss()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.findViewById<Button>(R.id.addWordCommit)?.setOnClickListener {
            (activity as DictionaryActivity).onWordCreated(
                generateId(),
                originalField.text.toString(),
                translationField.text.toString(),
                meaningField.text.toString()
            )
            dismiss()
        }
    }

    private fun generateId(): Long {
        return System.currentTimeMillis()
    }

    interface AddWordRouter {
        fun onWordCreated(id: Long, original: String, translation: String, meaning: String)
    }
}