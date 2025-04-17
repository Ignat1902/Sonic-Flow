package dev.ginger.music.database.dao

import androidx.room.Dao
import androidx.room.Query
import dev.ginger.music.database.models.TrackDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Query("SELECT * FROM TrackDBO")
    fun getChartTracks(): Flow<List<TrackDBO>>

    @Query("SELECT * FROM TrackDBO WHERE title LIKE '%' || :query || '%'")
    fun searchTrack(query: String): Flow<List<TrackDBO>>

}