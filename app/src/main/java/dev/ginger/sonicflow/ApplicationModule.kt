package dev.ginger.sonicflow

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.ginger.music.database.MusicDatabase
import dev.ginger.musicapi.MusicApi
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient() //  Создаем OkHttpClient с настройками по умолчанию
    }

    @Provides
    @Singleton
    fun provideMusicApi(okHttpClient: OkHttpClient): MusicApi {
        return MusicApi(
            baseUrl = "https://api.deezer.com",
            okHttpClient = okHttpClient
        )
    }

    @Provides
    @Singleton
    fun provideMusicDatabase(
        @ApplicationContext context: Context
    ): MusicDatabase {
        return MusicDatabase(context)
    }

    @Provides
    @Singleton
    fun provideExoPlayer(@ApplicationContext context: Context): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }

}