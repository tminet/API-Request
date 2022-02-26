package tmidev.apirequest.presentation.screen_album_photos

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import tmidev.apirequest.domain.model.Photo
import tmidev.apirequest.util.ImageLoader

class AlbumPhotosAdapter(
    private val imageLoader: ImageLoader
) : ListAdapter<Photo, AlbumPhotosViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AlbumPhotosViewHolder.create(parent = parent, imageLoader = imageLoader)

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