package dev.ginger.music.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.ginger.music.database.dao.TrackDao
import dev.ginger.music.database.models.TrackDBO

class MusicDatabase internal constructor(database: MusicRoomDatabase) {
    val trackDao: TrackDao = database.articlesDao()
}

@Database(entities = [TrackDBO::class], version = 1)
internal abstract class MusicRoomDatabase : RoomDatabase() {
    abstract fun articlesDao(): TrackDao
}

fun MusicDatabase(applicationContext: Context): MusicDatabase {
    val musicRoomDatabase =
        Room.databaseBuilder(
            checkNotNull(applicationContext.applicationContext),
            MusicRoomDatabase::class.java,
            "music"
        ).build()
    return MusicDatabase(musicRoomDatabase)
}