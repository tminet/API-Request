package tmidev.apirequest.presentation.screen_user_albums

import android.view.LayoutInflater
import android.view.ViewGroup
import tmidev.apirequest.R
import tmidev.apirequest.databinding.ItemAlbumBinding
import tmidev.apirequest.presentation.common.GenericViewHolder
import tmidev.apirequest.util.AlbumClick

class AlbumsViewHolder(
    binding: ItemAlbumBinding,
    private val albumClick: AlbumClick,
) : GenericViewHolder<AlbumItem>(binding) {
    private val textUserId = binding.textViewUserId
    private val textAlbumId = binding.textViewAlbumId
    private val textAlbumTitle = binding.textViewAlbumTitle
    private val cardAlbum = binding.root

    override fun bind(data: AlbumItem) {
        itemView.resources.run {
            textUserId.text = getString(R.string.userId, data.userId)
            textAlbumId.text = getString(R.string.albumId, data.id)
        }

        textAlbumTitle.text = data.title

        cardAlbum.setOnClickListener {
            albumClick.invoke(data.id)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            albumClick: AlbumClick,
        ): AlbumsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemAlbumBinding.inflate(inflater, parent, false)

            return AlbumsViewHolder(
                binding = itemBinding,
                albumClick = albumClick
            )
        }
    }
}