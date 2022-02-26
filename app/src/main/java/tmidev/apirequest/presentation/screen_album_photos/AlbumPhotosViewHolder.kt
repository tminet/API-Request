package tmidev.apirequest.presentation.screen_album_photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tmidev.apirequest.databinding.ItemAlbumPhotoBinding
import tmidev.apirequest.domain.model.Photo
import tmidev.apirequest.util.ImageLoader

class AlbumPhotosViewHolder(
    private val binding: ItemAlbumPhotoBinding,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(photo: Photo) = binding.run {
        imageLoader(
            imageView = imageViewPhoto,
            imageUrl = photo.url
        )

        textViewPhotoTitle.text = photo.title
    }

    companion object {
        fun create(
            parent: ViewGroup,
            imageLoader: ImageLoader
        ): AlbumPhotosViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemAlbumPhotoBinding.inflate(inflater, parent, false)

            return AlbumPhotosViewHolder(
                binding = itemBinding,
                imageLoader = imageLoader
            )
        }
    }
}