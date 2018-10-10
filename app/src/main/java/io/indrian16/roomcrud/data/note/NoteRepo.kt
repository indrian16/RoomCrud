package io.indrian16.roomcrud.data.note

import io.reactivex.Flowable

interface NoteRepo {

    fun getAllNote(): Flowable<List<Note>>

    fun getNoteById(noteId: Long): Flowable<Note>

    fun insertNote(note: Note)

    fun updateNote(note: Note)

    fun deleteNote(note: Note)

    fun deleteAllNote()
}