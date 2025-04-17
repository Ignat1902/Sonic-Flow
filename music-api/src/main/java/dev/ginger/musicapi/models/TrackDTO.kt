package dev.ginger.musicapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrackDTO(
    @SerialName("id") val id: Long,
    @SerialName("artist") val artist: ArtistDTO,
    @SerialName("title_short") val titleShort: String,
    @SerialName("title") val title: String,
    @SerialName("duration") val duration: Int,
    @SerialName("preview") val preview: String,
    @SerialName("album") val album: AlbumDTO,
    @SerialName("explicit_lyrics") val explicitLyrics: Boolean,
)