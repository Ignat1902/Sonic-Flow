package dev.ginger.music.player

import android.content.ComponentName
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.bumptech.glide.Glide
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import dagger.hilt.android.AndroidEntryPoint
import dev.ginger.music.player.databinding.FragmentMusicPlayerBinding
import dev.ginger.uikit.R
import java.util.Locale

@AndroidEntryPoint
class MusicPlayerFragment : Fragment() {

    private var _binding: FragmentMusicPlayerBinding? = null
    private val binding get() = _binding!!

    private var player: MediaController? = null
    private var controllerFuture: ListenableFuture<MediaController>? = null


    override fun onStart() {
        super.onStart()

        val sessionToken = SessionToken(
            requireContext(), ComponentName(requireContext(), MusicService::class.java)
        )

        controllerFuture = MediaController.Builder(requireContext(), sessionToken).buildAsync()
        controllerFuture?.addListener(
            {
                player = controllerFuture?.get()
                playTrack("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
            }, MoreExecutors.directExecutor()
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMusicPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val currentTimeTextView = binding.currentTimeTv
        val durationSongTextView = binding.durationSongTv

        val playButton = binding.playButton
        val pauseButton = binding.pauseButton

        durationSongTextView.text = player?.duration.toString()

        val songCoverImageView = binding.songCoverImageView
        val songTitleTextView = binding.songTitle
        val artistNameTextView = binding.artistName
        val albumNameTextView = binding.albumName

        Glide.with(this)
            .load("https://i.pinimg.com/474x/ae/ad/f1/aeadf162fb22d8fcbed4b861a71e8cdf.jpg?nii=t")
            .error(R.drawable.ic_placeholder_cover)
            .into(songCoverImageView)

        songTitleTextView.text = "внутри (feat. OG Buda)"
        albumNameTextView.text = "ОБА"
        artistNameTextView.text = "MAYOT"

        val seekBar = binding.seekBar
        seekBar.max = player?.duration?.toInt() ?: 0
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    player?.seekTo(progress.toLong())
                }
                currentTimeTextView.text = String.format(Locale.getDefault(), "%d", progress)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

        })


        playButton.setOnClickListener {
            if (player?.isPlaying == false) {
                player?.play()
                binding.playButton.visibility = View.GONE
                binding.pauseButton.visibility = View.VISIBLE
            }
        }

        pauseButton.setOnClickListener {
            if (player?.isPlaying == true) {
                player?.pause()
                binding.playButton.visibility = View.VISIBLE
                binding.pauseButton.visibility = View.GONE

                /*if (player == null) {
                    Toast.makeText(requireContext(), "Плеер не найден", Toast.LENGTH_SHORT).show()
                } else {

                }*/


            }

        }
    }

    private fun playTrack(url: String) {
        val mediaItem = MediaItem.fromUri(url)
        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.play()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        player = null
        controllerFuture = null
    }
}