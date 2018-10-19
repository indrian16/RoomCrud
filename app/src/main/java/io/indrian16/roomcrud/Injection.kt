package io.indrian16.roomcrud

import android.content.Context
import io.indrian16.roomcrud.data.AppDatabase
import io.indrian16.roomcrud.data.note.NoteRepo
import io.indrian16.roomcrud.data.note.NoteRepository


object Injection {

    fun provideNoteRepo(context: Context): NoteRepo {

        val db = AppDatabase.getInstance(context)
        return NoteRepository(db.noteDao())
    }
}