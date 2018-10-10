package io.indrian16.roomcrud.ui.base

import io.indrian16.roomcrud.data.note.Note
import io.indrian16.roomcrud.data.note.NoteRepository

interface BaseContract {

    interface Presenter {

        fun loadNote(repository: NoteRepository)

        fun deleteAll(repository: NoteRepository)

        fun unSubscribe()
    }

    interface View {

        fun noItem()

        fun updateNoteList(noteList: List<Note>)

        fun showError(error: String)
    }
}