package io.indrian16.roomcrud.ui.updatenote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import io.indrian16.roomcrud.Injection
import io.indrian16.roomcrud.R
import io.indrian16.roomcrud.data.note.Note
import io.indrian16.roomcrud.data.note.NoteRepository
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : UpdateContract.View, AppCompatActivity() {

    lateinit var presenter: UpdatePresenter
    private var repository: NoteRepository? = null

    private var updateNote = Note()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Init DB and Presenter
        repository = Injection.provideNoteRepository(this)
        presenter = UpdatePresenter(this, repository!!)

        //Get DB
        val noteId = intent.getLongExtra("noteId", -1)
        presenter.getNoteData(noteId)
    }

    fun updateText(view: View) {

        updateNote.text = edt_update_text.text.toString()
        presenter.updateNote(updateNote)
    }

    override fun invalidUpdateText(must: String) {

        edt_update_text.error = must
    }

    override fun getNoteViewData(note: Note) {

        updateNote = note
        edt_update_text.setText(updateNote.text)
        supportActionBar?.title = "Update NoteId ${updateNote.id}"
    }

    override fun showError(error: String) {

        Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
    }

    override fun onBackToBase() {

        finish()
    }

    override fun onSupportNavigateUp(): Boolean {

        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }
}
