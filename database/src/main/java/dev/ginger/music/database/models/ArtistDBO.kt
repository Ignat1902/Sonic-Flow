package dev.ginger.music.database.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ArtistDBO(
    @PrimaryKey val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("picture_small") val pictureSmall: String,
    @ColumnInfo("picture_medium") val pictureMedium: String,
    @ColumnInfo("picture_big") val pictureBig: String,
    @ColumnInfo("picture_xl") val pictureXl: String,
)
