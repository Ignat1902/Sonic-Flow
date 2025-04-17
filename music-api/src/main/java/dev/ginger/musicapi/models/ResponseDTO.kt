package dev.ginger.musicapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDTO<T>(
    @SerialName("data") val data: List<T>,
    @SerialName("total") val total: Int,
)