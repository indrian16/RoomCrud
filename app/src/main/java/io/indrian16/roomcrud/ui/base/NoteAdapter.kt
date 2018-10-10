package io.indrian16.roomcrud.ui.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.indrian16.roomcrud.R
import io.indrian16.roomcrud.data.note.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(private val listener: OnCustomClickListener) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private var noteList: List<Note> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {

        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.note_item, parent, false)
        return NoteHolder(root)
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {

        holder.bind(noteList[position])
    }

    fun addNote(noteList: List<Note>) {

        this.noteList = noteList
        notifyDataSetChanged()
    }

    fun deleteAll() {

        noteList = arrayListOf()
        notifyDataSetChanged()
    }

    inner class NoteHolder(root: View) : RecyclerView.ViewHolder(root) {

        fun bind(note: Note) = with(itemView) {

            tv_note_text.text = note.text
            tv_note_time.text = note.time

            itemView.setOnClickListener { listener.onClickItem(note.id!!) }
        }
    }

    interface OnCustomClickListener {

        fun onClickItem(noteId: Long)
    }
}