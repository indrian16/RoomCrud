package io.indrian16.roomcrud.ui.base

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import io.indrian16.roomcrud.Injection
import io.indrian16.roomcrud.ui.newnote.NewActivity
import io.indrian16.roomcrud.R
import io.indrian16.roomcrud.data.note.Note
import io.indrian16.roomcrud.data.note.NoteRepo
import io.indrian16.roomcrud.ui.updatenote.UpdateActivity
import io.indrian16.roomcrud.util.Constant
import kotlinx.android.synthetic.main.activity_base.*

class BaseActivity : BaseContract.View, AppCompatActivity() {

    private lateinit var presenter: BasePresenter
    private var repo: NoteRepo? = null

    private var adapter: NoteAdapter? = null

    override fun onResume() {
        super.onResume()
        presenter.loadNote()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        initView()

        // Init DB and Presenter
        repo = Injection.provideNoteRepo(this)
        presenter = BasePresenter(this, repo!!)
    }

    private fun initView() {

        // Adapter and getting position noteId
        adapter = NoteAdapter(object : NoteAdapter.OnCustomClickListener {

            override fun onClickItem(noteId: Long) {

                val intent = Intent(baseContext, UpdateActivity::class.java)
                intent.putExtra("noteId", noteId)
                startActivity(intent)
            }
        })

        // Sending to NewNote
        fab_new_note.setOnClickListener { startActivity(Intent(baseContext, NewActivity::class.java)) }

        // RecyclerView
        note_recycler_view.setHasFixedSize(true)
        note_recycler_view.layoutManager = LinearLayoutManager(baseContext,
                LinearLayout.VERTICAL, false)
        note_recycler_view.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.base_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            R.id.delete_all -> {

                presenter.deleteAll()
                adapter?.deleteAll()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun noItem() {

        tv_no_item.visibility = View.VISIBLE
        note_recycler_view.visibility = View.GONE
    }

    override fun updateNoteList(noteList: List<Note>) {

        if (!noteList.isEmpty()) {

            note_recycler_view.visibility = View.VISIBLE
            tv_no_item.visibility = View.INVISIBLE
            adapter?.addNote(noteList)

        } else {

            noItem()
        }
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
