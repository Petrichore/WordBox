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

class AddDictionaryFragment : DialogFragment() {

    private lateinit var dictionaryNameField: EditText
    private lateinit var dictionaryDescriptionField: EditText
    private lateinit var addDictionaryCommit: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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
            (activity as DictionaryCreationRouter).onDictionaryCreated(
                generateId(),
                dictionaryNameField.text.toString(),
                dictionaryDescriptionField.text.toString()
            )
            dismiss()
        }
    }

    private fun generateId(): Long {
        return System.currentTimeMillis()
    }

    interface DictionaryCreationRouter {
        fun onDictionaryCreated(id: Long, name: String, description: String)
    }
}