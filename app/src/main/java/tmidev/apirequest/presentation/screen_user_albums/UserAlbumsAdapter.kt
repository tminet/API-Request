package tmidev.apirequest.presentation.screen_user_albums

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import tmidev.apirequest.domain.model.Album
import tmidev.apirequest.util.AlbumClick

class UserAlbumsAdapter(
    private val albumClick: AlbumClick
) : ListAdapter<Album, UserAlbumsViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserAlbumsViewHolder.create(
            parent = parent,
            albumClick = albumClick
        )

    override fun onBindViewHolder(holder: UserAlbumsViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Album, newItem: Album) =
                oldItem == newItem
        }
    }
}