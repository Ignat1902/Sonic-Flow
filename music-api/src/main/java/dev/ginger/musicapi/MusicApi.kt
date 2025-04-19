package dev.ginger.musicapi

import retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.ginger.musicapi.models.ResponseDTO
import dev.ginger.musicapi.models.TrackDTO
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Api Documentation [here](https://developers.deezer.com/api)
 */
interface MusicApi {

    /**
     * Api chart methods [here](https://developers.deezer.com/api/chart#connections)
     */
    @GET("/chart/0/tracks")
    suspend fun getChartTracks(): Result<ResponseDTO<TrackDTO>>

    /**
     * Api details [here](https://developers.deezer.com/api/search)
     */
    @GET("/search")
    suspend fun searchTracks(
        @Query("q") query: String? = null,
    ): Result<ResponseDTO<TrackDTO>>
}

fun MusicApi(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
): MusicApi = retrofit(baseUrl, okHttpClient).create()


/**
 * Create instance retrofit
 *
 * @param ignoreUnknownKeys is used to ignore unnecessary data in Json.
 */
private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
): Retrofit {
    val contentType = "application/json".toMediaType()
    val json = Json {
        ignoreUnknownKeys = true
    }
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .run { if (okHttpClient != null) client(okHttpClient) else this }
        .addConverterFactory(json.asConverterFactory(contentType))
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .build()
}
