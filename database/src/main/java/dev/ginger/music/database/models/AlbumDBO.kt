package dev.ginger.music.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class AlbumDBO(
    val id: Long,
    val title: String,
    val coverMedium: String,
    val coverBig: String,
)

