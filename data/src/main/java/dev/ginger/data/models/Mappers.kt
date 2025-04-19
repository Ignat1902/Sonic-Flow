package dev.ginger.data.models

import dev.ginger.data.RequestResult
import dev.ginger.music.database.models.AlbumDBO
import dev.ginger.music.database.models.ArtistDBO
import dev.ginger.music.database.models.TrackDBO
import dev.ginger.musicapi.models.AlbumDTO
import dev.ginger.musicapi.models.ArtistDTO
import dev.ginger.musicapi.models.TrackDTO


fun <I : Any, O : Any> RequestResult<I>.map(mapper: (I) -> O): RequestResult<O> {
    return when (this) {
        is RequestResult.Success -> RequestResult.Success(mapper(data))
        is RequestResult.Error -> RequestResult.Error(message)
        is RequestResult.Loading -> RequestResult.Loading(data?.let(mapper))
    }
}

internal fun TrackDBO.toTrack(): Track {
    return Track(
        id = id,
        artist = artist.toArtist(),
        titleShort = titleShort,
        title = title,
        duration = duration,
        preview = preview,
        album = album.toAlbum(),
        explicitLyrics = explicitLyrics
    )
}

internal fun TrackDTO.toTrack(): Track {
    return Track(
        id = id,
        artist = artist.toArtist(),
        titleShort = titleShort,
        title = title,
        duration = duration,
        preview = preview,
        album = album.toAlbum(),
        explicitLyrics = explicitLyrics
    )
}


internal fun TrackDTO.toTrackDBO(): TrackDBO {
    return TrackDBO(
        id = id,
        artist = artist.toArtistDBO(),
        titleShort = titleShort,
        title = title,
        duration = duration,
        preview = preview,
        album = album.toAlbumDBO(),
        explicitLyrics = explicitLyrics
    )
}

internal fun Track.toTrackDBO(): TrackDBO {
    return TrackDBO(
        id = id,
        artist = artist.toArtistDBO(),
        titleShort = titleShort,
        title = title,
        duration = duration,
        preview = preview,
        album = album.toAlbumDBO(),
        explicitLyrics = explicitLyrics
    )
}

internal fun ArtistDTO.toArtist(): Artist {
    return Artist(
        id = id,
        name = name,
    )
}

internal fun ArtistDTO.toArtistDBO(): ArtistDBO {
    return ArtistDBO(
        id = id,
        name = name,
    )
}

internal fun ArtistDBO.toArtist(): Artist {
    return Artist(
        id = id,
        name = name,
    )
}

internal fun Artist.toArtistDBO(): ArtistDBO {
    return ArtistDBO(
        id = id,
        name = name,
    )
}

internal fun Album.toAlbumDBO(): AlbumDBO {
    return AlbumDBO(
        id = id,
        title = title,
        coverMedium = coverMedium,
        coverBig = coverBig,
    )
}

internal fun AlbumDTO.toAlbumDBO(): AlbumDBO {
    return AlbumDBO(
        id = id,
        title = title,
        coverMedium = coverMedium,
        coverBig = coverBig,
    )
}

internal fun AlbumDBO.toAlbum(): Album {
    return Album(
        id = id,
        title = title,
        coverMedium = coverMedium,
        coverBig = coverBig,
    )
}

internal fun AlbumDTO.toAlbum(): Album {
    return Album(
        id = id,
        title = title,
        coverMedium = coverMedium,
        coverBig = coverBig,
    )
}


