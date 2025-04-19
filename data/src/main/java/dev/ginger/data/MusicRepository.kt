package dev.ginger.data

import dev.ginger.data.models.Track
import dev.ginger.data.models.toTrack
import dev.ginger.music.database.MusicDatabase
import dev.ginger.musicapi.MusicApi
import dev.ginger.musicapi.models.ResponseDTO
import dev.ginger.musicapi.models.TrackDTO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MusicRepository @Inject constructor(
//    private val database: MusicDatabase,
    private val api: MusicApi,
) {

    fun getChart(): Flow<RequestResult<List<Track>>> = flow {
        //Оповещаем о начале загрузки данных
        emit(RequestResult.Loading(null))
        delay(1000L)

        //Получаем от сервера ответ
        val remoteChart: Result<ResponseDTO<TrackDTO>> = api.getChartTracks()

        //Вызываем функцию для обработки результата с сервера
        remoteChart.onSuccess {
            val trackList = remoteChart.getOrThrow().data.map { it.toTrack() }
            emit(RequestResult.Success(trackList))
        }.onFailure {
            when (it) {
                is IOException -> {
                    emit(
                        RequestResult.Error(
                            "Сервер не отвечает. Проверьте своё интернет соединение. \nДетали: ${it.message.toString()}"
                        )
                    )
                }

                else -> {
                    emit(RequestResult.Error("Произошла ошибка: ${it.message}"))
                }
            }
        }
    }


    fun searchTrack(query: String): Flow<RequestResult<List<Track>>> = flow {
        emit(RequestResult.Loading(null))

        val remote: Result<ResponseDTO<TrackDTO>> = api.searchTracks(query)

        remote.onSuccess {
            val trackList = remote.getOrThrow().data.map { it.toTrack() }
            emit(RequestResult.Success(trackList))
        }.onFailure {
            when (it) {
                is IOException -> {
                    emit(
                        RequestResult.Error(
                            "Сервер не отвечает. Проверьте своё интернет соединение. \nДетали: ${it.message.toString()}"
                        )
                    )
                }

                else -> {
                    emit(RequestResult.Error("Произошла ошибка: ${it.message}"))
                }
            }
        }
    }


}

/**
 * Обертка над данными, чтобы обрабатывать ошибки
 */
sealed class RequestResult<E>(open val data: E? = null, val message: String? = null) {

    class Loading<E>(data: E? = null) : RequestResult<E>(data)
    class Success<E>(override val data: E) : RequestResult<E>(data)
    class Error<E>(message: String?) : RequestResult<E>(message = message)
}