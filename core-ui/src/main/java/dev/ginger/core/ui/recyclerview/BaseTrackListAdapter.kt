package dev.ginger.core.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ginger.core.ui.R
import dev.ginger.core.ui.databinding.BaseTrackListItemBinding
import dev.ginger.core.ui.models.TrackUI

class BaseTrackListAdapter(private val onClickItem: () -> Unit) :
    RecyclerView.Adapter<BaseTrackListAdapter.BaseTrackListViewHolder>() {

    var trackList = emptyList<TrackUI>()
        set(value) {
            val callback = CommonCallbackImpl(
                field,
                value,
                areItemsTheSame = { oldItem, newItem ->
                    oldItem.id == newItem.id
                }
            )
            field = value
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
        }


    inner class BaseTrackListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val coverIV =
            itemView.findViewById<ImageView>(R.id.cover_image_view)
        private val trackNameTV =
            itemView.findViewById<TextView>(R.id.track_name_text_view)
        private val artistNameTV =
            itemView.findViewById<TextView>(R.id.artist_name_text_view)
        private val explicitContentIV =
            itemView.findViewById<ImageView>(R.id.cover_explicit_content)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClickItem()
                }
            }
        }

        fun onBind(track: TrackUI) {
            Glide.with(itemView.context)
                .load(track.coverUrl)
                .error(R.drawable.ic_placeholder_cover)
                .into(coverIV)
            trackNameTV.text = track.titleShort
            artistNameTV.text = track.artist
            if (track.explicitLyrics) {
                explicitContentIV.visibility = View.VISIBLE
            } else {
                explicitContentIV.visibility = View.GONE
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseTrackListViewHolder {
        val binding =
            BaseTrackListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BaseTrackListViewHolder(binding.root)
    }

    override fun getItemCount(): Int = trackList.size


    override fun onBindViewHolder(holder: BaseTrackListViewHolder, position: Int) {
        holder.onBind(trackList[position])
    }
}
