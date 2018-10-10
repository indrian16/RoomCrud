package io.indrian16.roomcrud.data.note

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes")
data class Note (

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long?,

        @ColumnInfo(name = "text")
        var text: String,

        @ColumnInfo(name = "time")
        var time: String
) {

    constructor():this(null, "", "")
}