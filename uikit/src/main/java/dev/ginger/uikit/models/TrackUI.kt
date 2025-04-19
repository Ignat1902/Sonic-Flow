package dev.ginger.uikit.models

data class TrackUI(
    val id: Long,
    val artist: String,
    val titleShort: String,
    val title: String,
    val albumName: String,
    val explicitLyrics: Boolean,
    val duration: Int,
    val preview: String,
    val coverUrl: String,
)
