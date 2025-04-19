package dev.ginger.music.main

import dev.ginger.data.RequestResult
import dev.ginger.data.models.Track
import dev.ginger.uikit.models.TrackUI

internal fun RequestResult<List<TrackUI>>.toState(): State {
    return when (this) {
        is RequestResult.Error -> State.Error(tracks = data, errorMessage = message)
        is RequestResult.Loading -> State.Loading(tracks = data)
        is RequestResult.Success -> State.Success(tracks = data)
    }
}

internal fun Track.toTrackUI(): TrackUI {
    return TrackUI(
        id = id,
        artist = artist.name,
        titleShort = titleShort,
        title = title,
        albumName = album.title,
        explicitLyrics = explicitLyrics,
        duration = duration,
        preview = preview,
        coverUrl = album.coverMedium,
    )
}