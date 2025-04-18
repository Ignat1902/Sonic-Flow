package dev.ginger.music.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.ginger.music.database.models.TrackDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Query("SELECT * FROM TrackDBO")
    suspend fun getChartTracks(): List<TrackDBO>

    @Query("SELECT * FROM TrackDBO")
    fun observeAll(): Flow<List<TrackDBO>>

    @Query("SELECT * FROM TrackDBO WHERE title LIKE '%' || :query || '%'")
    suspend fun searchTrack(query: String):List<TrackDBO>

    @Insert
    suspend fun insert(tracks: List<TrackDBO>)

    @Delete
    suspend fun remove(tracks: List<TrackDBO>)

}