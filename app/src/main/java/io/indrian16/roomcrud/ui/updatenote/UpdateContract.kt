package io.indrian16.roomcrud.ui.updatenote

import io.indrian16.roomcrud.data.note.Note
import io.indrian16.roomcrud.data.note.NoteRepository

interface UpdateContract {

    interface Presenter {

        fun getNoteData(repository: NoteRepository, noteId: Long)

        fun updateNote(repository: NoteRepository, note: Note)

        fun unSubscribe()
    }

    interface View {

        fun getNoteViewData(note: Note)

        fun invalidUpdateText(must: String)

        fun showError(error: String)

        fun onBackToBase()
    }
}