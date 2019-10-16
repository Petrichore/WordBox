package by.ctefi.wordbox.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import by.ctefi.wordbox.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        openLibBtn.setOnClickListener {
            startNewActivity(DictionaryListActivity::class.java)
        }

        //TODO this opportunity should be put in dictionary list
        newDictionaryBtn.setOnClickListener{
            startNewActivity(DictionaryCreationActivity::class.java)
        }
    }

    private fun startNewActivity(activity: Class<out Activity>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}