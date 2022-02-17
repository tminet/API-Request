package tmidev.apirequest.presentation.screen_album_photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tmidev.apirequest.databinding.ItemAlbumPhotoBinding
import tmidev.apirequest.domain.model.Photo

class AlbumPhotosViewHolder(
    private val binding: ItemAlbumPhotoBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(photo: Photo) = binding.run {
        imageViewPhoto.load(photo.url)
        textViewPhotoTitle.text = photo.title
    }

    companion object {
        fun create(parent: ViewGroup): AlbumPhotosViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemAlbumPhotoBinding.inflate(inflater, parent, false)
            return AlbumPhotosViewHolder(binding = itemBinding)
        }
    }
}