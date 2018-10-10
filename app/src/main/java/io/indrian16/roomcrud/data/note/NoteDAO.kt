package io.indrian16.roomcrud.data.note

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface NoteDAO {

    @Query("select * from notes")
    fun getAllNote(): Flowable<List<Note>>

    @Query("select * from notes where id = :noteId")
    fun getNoteById(noteId: Long): Flowable<Note>

    @Insert
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("delete from notes")
    fun deleteAllNote()
}