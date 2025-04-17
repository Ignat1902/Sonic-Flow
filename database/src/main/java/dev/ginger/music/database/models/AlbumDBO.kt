package dev.ginger.music.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlbumDBO(
    @PrimaryKey val id: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("cover_small") val coverSmall: String,
    @ColumnInfo("cover_medium") val coverMedium: String,
    @ColumnInfo("cover_big") val coverBig: String,
    @ColumnInfo("cover_xl") val coverXl: String,
)

