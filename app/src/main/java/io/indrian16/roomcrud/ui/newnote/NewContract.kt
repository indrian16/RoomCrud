package io.indrian16.roomcrud.ui.newnote

import io.indrian16.roomcrud.data.note.Note

interface NewContract {

    interface Presenter {

        fun saveText(note: Note)

        fun unSubscribe()
    }

    interface View {

        fun validationSaveText(must: String)

        fun onBackToBase()

        fun showError(error: String)
    }
}