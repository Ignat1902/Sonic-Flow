package dev.ginger.uikit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ginger.uikit.databinding.FragmentBaseTrackListBinding
import dev.ginger.uikit.models.TrackUI
import dev.ginger.uikit.recyclerview.BaseTrackListAdapter

abstract class BaseTrackListFragment : Fragment() {

    private var _binding: FragmentBaseTrackListBinding? = null
    protected val binding get() = _binding!!

    private val adapter = BaseTrackListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBaseTrackListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.trackListRecycler.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
            it.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

    }

    protected fun updateData(data: List<TrackUI>) {
        adapter.trackList = data
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}