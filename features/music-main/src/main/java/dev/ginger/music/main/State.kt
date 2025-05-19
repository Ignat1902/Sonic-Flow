package dev.ginger.music.main

import dev.ginger.core.ui.models.TrackUI

sealed class State(val tracks: List<TrackUI>?, val errorMessage: String? = null) {

    data object None : State(tracks = null)

    class Loading(tracks: List<TrackUI>? = null) : State(tracks)

    class Error(tracks: List<TrackUI>? = null, errorMessage: String? = null) :
        State(tracks, errorMessage)

    class Success(tracks: List<TrackUI>) : State(tracks)
}