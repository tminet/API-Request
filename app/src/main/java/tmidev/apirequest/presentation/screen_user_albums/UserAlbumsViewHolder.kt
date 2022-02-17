package tmidev.apirequest.presentation.screen_user_albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tmidev.apirequest.R
import tmidev.apirequest.databinding.ItemUserAlbumBinding
import tmidev.apirequest.domain.model.Album
import tmidev.apirequest.util.AlbumClick

class UserAlbumsViewHolder(
    private val binding: ItemUserAlbumBinding,
    private val albumClick: AlbumClick?,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(album: Album) = binding.run {
        textViewUserId.text = itemView.resources.getString(
            R.string.userId, album.userId
        )

        textViewAlbumId.text = itemView.resources.getString(
            R.string.albumId, album.id
        )

        textViewAlbumTitle.text = album.title

        binding.root.setOnClickListener {
            albumClick?.invoke(album.id)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            albumClick: AlbumClick?,
        ): UserAlbumsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemUserAlbumBinding.inflate(inflater, parent, false)
            return UserAlbumsViewHolder(
                binding = itemBinding,
                albumClick = albumClick
            )
        }
    }
}