package dev.ginger.music.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ginger.music.main.State
import dev.ginger.music.main.domain.GetChartUseCase
import dev.ginger.music.main.domain.SearchTrackUseCase
import dev.ginger.music.main.toState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChartTracksViewModel @Inject constructor(
    private val getChartUseCase: GetChartUseCase,
    private val searchTrackUseCase: SearchTrackUseCase,
) : ViewModel() {

    var searchQuery = MutableStateFlow("")

    private var _displayedTracksState = MutableStateFlow<State>(State.None)
    val displayedTracksState: StateFlow<State> = _displayedTracksState

    private var searchJob: Job? = null

    init {
        loadChartTracks()
    }

    fun loadChartTracks() {
        viewModelScope.launch {
            delay(500)
            getChartUseCase.invoke().map { it.toState() }
                .collect { state ->
                    _displayedTracksState.value = state
                }
        }
    }

    fun onSearch() {
        searchJob?.cancel() //  Отменяем предыдущий поисковый запрос так как пользователь ввел что-то в текстовое поле

        if (searchQuery.value.isBlank()) {
            loadChartTracks()
        } else {
            searchJob = viewModelScope.launch {
                delay(500)
                searchTrackUseCase.invoke(searchQuery.value).map { it.toState() }
                    .collect { state ->
                        _displayedTracksState.value = state
                    }
            }
        }
    }


}
