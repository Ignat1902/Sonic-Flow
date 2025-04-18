package dev.ginger.music.database.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ArtistDBO(
    val id: Long,
    val name: String,
)
