package io.indrian16.roomcrud.data.note

import io.reactivex.Flowable

class NoteRepository(private val dao: NoteDAO) : NoteRepo {

    override fun getAllNote(): Flowable<List<Note>> {

        return dao.getAllNote()
    }

    override fun getNoteById(noteId: Long): Flowable<Note> {

        return dao.getNoteById(noteId)
    }

    override fun insertNote(note: Note) {

        dao.insertNote(note)
    }

    override fun updateNote(note: Note) {

        dao.updateNote(note)
    }

    override fun deleteNote(note: Note) {

        dao.deleteNote(note)
    }

    override fun deleteAllNote() {

        dao.deleteAllNote()
    }
}