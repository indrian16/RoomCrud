package io.indrian16.roomcrud.ui.newnote

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import io.indrian16.roomcrud.Injection
import io.indrian16.roomcrud.R
import io.indrian16.roomcrud.data.note.Note
import io.indrian16.roomcrud.data.note.NoteRepository
import io.indrian16.roomcrud.util.Constant
import kotlinx.android.synthetic.main.activity_new.*
import java.text.SimpleDateFormat
import java.util.*

class NewActivity : NewContract.View, AppCompatActivity() {

    private lateinit var presenter: NewPresenter
    private var repository: NoteRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        // ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Init DB Presenter
        repository = Injection.provideNoteRepository(this)
        presenter = NewPresenter(this, repository!!)
    }

    override fun onSupportNavigateUp(): Boolean {

        finish()
        return true
    }

    @SuppressLint("SimpleDateFormat")
    fun saveText(view: View) {

        val newData = Note()

        // Input Text
        newData.text = edt_new_text.text.toString()

        //TimeNow
        val date = Date()
        val formatter = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
        val timeNow = formatter.format(date)
        newData.time = timeNow

        presenter.saveText(newData)
    }

    override fun validationSaveText(must: String) {

        edt_new_text.error = must
    }

    override fun onBackToBase() {

        finish()
    }

    override fun showError(error: String) {

        Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        Log.e(Constant.TAG_ERROR, error)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }
}
