package dev.ginger.data

import dev.ginger.data.models.Track
import dev.ginger.data.models.toTrack
import dev.ginger.data.models.toTrackDBO
import dev.ginger.music.database.MusicDatabase
import dev.ginger.musicapi.MusicApi
import dev.ginger.musicapi.models.ResponseDTO
import dev.ginger.musicapi.models.TrackDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class MusicRepository(
    private val database: MusicDatabase,
    private val api: MusicApi,
) {

    fun getChart(): Flow<RequestResult<List<Track>>> = flow {
        //Оповещаем о начале загрузки данных
        emit(RequestResult.Loading(null))

        val localChart = database.trackDao.getChartTracks().map { it.toTrack() }
        emit(RequestResult.Loading(data = localChart))

        //Получаем от сервера ответ
        val remoteChart: Result<ResponseDTO<TrackDTO>> = api.getChartTracks()

        //Вызываем функцию для обработки результата с сервера
        remoteChart.runCatching {
            //Добавляем в базу данных данные с сервера
            database.trackDao.insert(remoteChart
                .getOrThrow().data
                .map { it.toTrackDBO() })
        }.onFailure {
            when (it) {
                is IOException -> {
                    emit(
                        RequestResult.Error(
                            localChart,
                            "Сервер не отвечает. Проверьте своё интернет соединение."
                        )
                    )
                }

                else -> {
                    emit(RequestResult.Error(localChart, "Произошла ошибка: ${it.message}"))
                }
            }
        }
        //Получаем актуальные данные с базы данных
        val result = database.trackDao.getChartTracks().map { it.toTrack() }
        emit(RequestResult.Success(result))
    }


    fun searchTrack(query: String): Flow<RequestResult<List<Track>>> = flow {
        //Оповещаем о начале загрузки данных
        emit(RequestResult.Loading(null))

        val localTrack = database.trackDao.searchTrack(query).map { it.toTrack() }
        emit(RequestResult.Loading(data = localTrack))

        //Получаем от сервера ответ
        val remoteTrack: Result<ResponseDTO<TrackDTO>> = api.searchTracks(query)

        //Вызываем функцию для обработки результата с сервера
        remoteTrack.runCatching {
            //Добавляем в базу данных данные с сервера
            database.trackDao.insert(remoteTrack
                .getOrThrow().data
                .map { it.toTrackDBO() })
        }.onFailure {
            when (it) {
                is IOException -> {
                    emit(
                        RequestResult.Error(
                            localTrack,
                            "Сервер не отвечает. Проверьте своё интернет соединение."
                        )
                    )
                }

                else -> {
                    emit(RequestResult.Error(localTrack, "Произошла ошибка: ${it.message}"))
                }
            }
        }
        //Получаем актуальные данные с базы данных
        val result = database.trackDao.searchTrack(query).map { it.toTrack() }
        emit(RequestResult.Success(result))
    }


}

/**
 * Обертка над данными, чтобы обрабатывать ошибки
 */
sealed class RequestResult<E>(protected val data: E?) {

    class Loading<E>(data: E?) : RequestResult<E>(data)
    class Success<E>(data: E?) : RequestResult<E>(data)
    class Error<E>(data: E?, message: String?) : RequestResult<E>(data)
}