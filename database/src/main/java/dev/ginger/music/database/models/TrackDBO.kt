package dev.ginger.music.database.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ArtistDBO::class,
            parentColumns = ["id"],
            childColumns = ["artistId"]
        ), ForeignKey(
            entity = AlbumDBO::class,
            parentColumns = ["id"],
            childColumns = ["albumId"]
        )]
)
data class TrackDBO(
    @PrimaryKey val id: Long,
    @ColumnInfo("artistId") val artist: Long,
    @ColumnInfo("title_short") val titleShort: String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("duration") val duration: Int,
    @ColumnInfo("preview") val preview: String,
    @ColumnInfo("albumId") val album: Long,
    @ColumnInfo("explicit_lyrics") val explicitLyrics: Boolean,
)