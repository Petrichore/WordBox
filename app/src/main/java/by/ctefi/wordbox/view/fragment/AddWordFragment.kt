package by.ctefi.wordbox.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import by.ctefi.wordbox.R
import by.ctefi.wordbox.entity.Word
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

            val original: String = originalField.text.toString()
            val translation: String = translationField.text.toString()
            val meaning: String = meaningField.text.toString()

            if (inputValidation(original, translation, meaning)) {
                (activity as DictionaryActivity).onWordCreated(
                    Word(generateId(), original, translation, meaning)
                )
                dismiss()
            } else {
                Toast.makeText(activity, "Unsuitable data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inputValidation(original: String, translation: String, meaning: String): Boolean {
        return !(original.isBlank() && translation.isBlank() && meaning.isBlank())
    }

    private fun generateId(): Long {
        return System.currentTimeMillis()
    }

    interface CommitDialog {
        fun onWordCreated(word: Word)
    }
}