package io.indrian16.roomcrud

import android.content.Context
import io.indrian16.roomcrud.data.AppDatabase
import io.indrian16.roomcrud.data.note.NoteRepository


object Injection {

    fun provideNoteRepository(context: Context): NoteRepository {

        val db = AppDatabase.getInstance(context)
        return NoteRepository(db.noteDao())
    }
}