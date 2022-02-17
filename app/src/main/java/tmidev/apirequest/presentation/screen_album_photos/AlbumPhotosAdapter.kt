package tmidev.apirequest.presentation.screen_album_photos

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import tmidev.apirequest.domain.model.Photo

class AlbumPhotosAdapter : ListAdapter<Photo, AlbumPhotosViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AlbumPhotosViewHolder.create(parent = parent)

    override fun onBindViewHolder(holder: AlbumPhotosViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo) =
                oldItem == newItem
        }
    }
}