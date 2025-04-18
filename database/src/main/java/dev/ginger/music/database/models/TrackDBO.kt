package dev.ginger.music.database.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackDBO(
    @PrimaryKey val id: Long,
    @Embedded("artist") val artist: ArtistDBO,
    @ColumnInfo("title_short") val titleShort: String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("duration") val duration: Int,
    @ColumnInfo("preview") val preview: String,
    @Embedded("album") val album: AlbumDBO,
    @ColumnInfo("explicit_lyrics") val explicitLyrics: Boolean,
)
