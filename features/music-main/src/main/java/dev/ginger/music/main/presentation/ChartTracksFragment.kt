package dev.ginger.music.main.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import dev.ginger.music.main.R
import dev.ginger.music.main.State
import dev.ginger.uikit.BaseTrackListFragment
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChartTracksFragment : BaseTrackListFragment() {

    private val viewModel: ChartTracksViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString() ?: ""
                viewModel.searchQuery.value = query
                viewModel.onSearch()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.displayedTracksState.collect { state ->
                    when (state) {
                        is State.Success -> {
                            binding.progressBar.isVisible = false
                            binding.errorMessage.isVisible = false
                            binding.trackListRecycler.isVisible = true
                            updateData(state.tracks ?: emptyList())
                        }

                        is State.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.errorMessage.isVisible = false
                            binding.trackListRecycler.isVisible = false
                        }

                        is State.Error -> {
                            binding.progressBar.isVisible = false
                            binding.errorMessage.isVisible = true
                            binding.trackListRecycler.isVisible = false
                            binding.errorMessage.text =
                                state.errorMessage + "\nПотяните экран вниз, чтобы повторить"
                            showAlertDialog { viewModel.loadChartTracks() }
                        }

                        State.None -> {}
                    }
                }
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            if (viewModel.searchQuery.value.isNotBlank()) {
                viewModel.onSearch()
            } else {
                viewModel.loadChartTracks()
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }

    }

    private fun showAlertDialog(onPositiveClick: () -> Unit) {
        MaterialAlertDialogBuilder(requireContext())
            .setIcon(R.drawable.ic_warning)
            .setTitle(resources.getString(R.string.title_alert_dialog))
            .setMessage(resources.getString(R.string.supporting_text_alert_dialog))
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                dialog.dismiss()

            }

            .setPositiveButton(resources.getString(R.string.retry)) { dialog, which ->
                onPositiveClick()
                dialog.dismiss()
            }
            .show()
    }
}
