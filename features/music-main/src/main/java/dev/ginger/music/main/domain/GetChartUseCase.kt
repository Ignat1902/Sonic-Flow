package dev.ginger.music.main.domain

import dev.ginger.data.MusicRepository
import dev.ginger.data.models.map
import dev.ginger.music.main.toTrackUI
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetChartUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {
    internal fun invoke() = musicRepository.getChart().map { requestResult ->
        requestResult.map { tracks ->
            tracks.map { it.toTrackUI() }
        }
    }
}