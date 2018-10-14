package io.indrian16.roomcrud.ui.updatenote

import io.indrian16.roomcrud.data.note.Note

interface UpdateContract {

    interface Presenter {

        fun getNoteData(noteId: Long)

        fun updateNote(note: Note)

        fun unSubscribe()
    }

    interface View {

        fun getNoteViewData(note: Note)

        fun invalidUpdateText(must: String)

        fun showError(error: String)

        fun onBackToBase()
    }
}