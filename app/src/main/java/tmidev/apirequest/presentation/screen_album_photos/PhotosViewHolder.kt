package tmidev.apirequest.presentation.screen_album_photos

import android.view.LayoutInflater
import android.view.ViewGroup
import tmidev.apirequest.databinding.ItemPhotoBinding
import tmidev.apirequest.presentation.common.GenericViewHolder
import tmidev.apirequest.util.ImageLoader

class PhotosViewHolder(
    binding: ItemPhotoBinding,
    private val imageLoader: ImageLoader
) : GenericViewHolder<PhotoItem>(binding) {
    private val imagePhoto = binding.imageViewPhoto
    private val textPhotoTitle = binding.textViewPhotoTitle

    override fun bind(data: PhotoItem) {
        imageLoader(
            imageView = imagePhoto,
            imageUrl = data.url
        )

        textPhotoTitle.text = data.title
    }

    companion object {
        fun create(
            parent: ViewGroup,
            imageLoader: ImageLoader
        ): PhotosViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPhotoBinding.inflate(inflater, parent, false)

            return PhotosViewHolder(
                binding = itemBinding,
                imageLoader = imageLoader
            )
        }
    }
}