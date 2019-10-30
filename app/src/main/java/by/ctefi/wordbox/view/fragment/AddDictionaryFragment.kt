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
import by.ctefi.wordbox.entity.Dictionary

class AddDictionaryFragment : DialogFragment() {

    private lateinit var dictionaryNameField: EditText
    private lateinit var dictionaryDescriptionField: EditText
    private lateinit var addDictionaryCommit: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_add_dictionary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dictionaryNameField = view.findViewById(R.id.addDictionaryName)
        dictionaryDescriptionField = view.findViewById(R.id.addDictionaryDescription)
        addDictionaryCommit = view.findViewById(R.id.addDictionaryCommit)

        view.findViewById<Button>(R.id.addDictionaryCancel).setOnClickListener {
            dismiss()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addDictionaryCommit.setOnClickListener {
            val name = dictionaryNameField.text.toString()

            if (inputValidation(name)) {
                val id = generateId()
                (activity as CommitDictionaryCreation).onDictionaryCreated(Dictionary(id, name))
                dismiss()
            } else {
                Toast.makeText(activity, "Unsuitable data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inputValidation(name: String): Boolean {
        return !name.isBlank()
    }

    private fun generateId(): Long {
        return System.currentTimeMillis()
    }

    interface CommitDictionaryCreation {
        fun onDictionaryCreated(dictionary: Dictionary)
    }
}