package dev.ginger.data.models

data class Track(
    val id: Long,
    val artist: Artist,
    val titleShort: String,
    val title: String,
    val duration: Int,
    val preview: String,
    val album: Album,
    val explicitLyrics: Boolean,
)