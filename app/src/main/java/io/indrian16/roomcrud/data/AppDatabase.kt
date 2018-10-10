package io.indrian16.roomcrud.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import io.indrian16.roomcrud.data.note.Note
import io.indrian16.roomcrud.data.note.NoteDAO
import io.indrian16.roomcrud.util.Constant

@Database(entities = [(Note::class)], version = Constant.DB_VER)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDAO

    companion object {

        private var mInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            synchronized(AppDatabase::class) {

                if (mInstance == null) {

                    mInstance = Room.databaseBuilder(context, AppDatabase::class.java, Constant.DB_NAME)
                            .allowMainThreadQueries()
                            .build()
                }
            }

            return mInstance!!
        }
    }
}