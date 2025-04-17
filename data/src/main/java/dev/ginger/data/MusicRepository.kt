package dev.ginger.data

import dev.ginger.music.database.MusicDatabase
import dev.ginger.musicapi.MusicApi

class MusicRepository(
    private val database: MusicDatabase,
    private val api: MusicApi,
) {

    fun getAll() {}

    fun getAllFromApi() {}

    fun getAllFromDatabase() {}

    fun searchTrackFromApi(query: String) {}

    fun searchTrackFromDatabase(query: String) {}

}