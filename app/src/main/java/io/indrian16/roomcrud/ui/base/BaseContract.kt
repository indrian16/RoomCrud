package io.indrian16.roomcrud.ui.base

import io.indrian16.roomcrud.data.note.Note

interface BaseContract {

    interface Presenter {

        fun loadNote()

        fun deleteAll()

        fun unSubscribe()
    }

    interface View {

        fun noItem()

        fun updateNoteList(noteList: List<Note>)

        fun showError(error: String)
    }
}