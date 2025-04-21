package dev.ginger.music.player

import android.content.ComponentName
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import dagger.hilt.android.AndroidEntryPoint
import dev.ginger.music.player.databinding.FragmentMusicPlayerBinding

@AndroidEntryPoint
class MusicPlayerFragment : Fragment() {

    private var _binding: FragmentMusicPlayerBinding? = null
    private val binding get() = _binding!!

    private var mediaController: MediaController? = null
    private var controllerFuture: ListenableFuture<MediaController>? = null


    override fun onStart() {
        super.onStart()

        val sessionToken = SessionToken(
            requireContext(), ComponentName(requireContext(), MusicService::class.java)
        )

        controllerFuture = MediaController.Builder(requireContext(), sessionToken).buildAsync()
        controllerFuture?.addListener({
            mediaController = controllerFuture?.get()
            if (mediaController != null) {

                // Создание и добавление медиаэлемента\
                val artworkUri =
                    Uri.parse("https://cdn-images.dzcdn.net/images/cover/5718f7c81c27e0b2417e2a4c45224f8a/250x250-000000-80-0-0.jpg")
                val metaData = MediaMetadata.Builder()
                    .setTitle("Android")
                    .setArtist("Google")
                    .setAlbumTitle("Album")
                    .setArtworkUri(artworkUri)
                    .build()


                val testUri =
                    Uri.parse("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
                val mediaItem = MediaItem.Builder()
                    .setUri(testUri)
                    .setMediaMetadata(metaData)
                    .build()


                mediaController?.addMediaItem(mediaItem)


                // Подготовка и воспроизведение
                mediaController?.prepare()
                mediaController?.play()

            } else {
                Log.e("MainActivity", "Failed to initialize MediaController")
            }
        }, MoreExecutors.directExecutor())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMusicPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}