package tmidev.apirequest.presentation.screen_user_posts

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import tmidev.apirequest.domain.model.Post

class UserPostsAdapter : ListAdapter<Post, UserPostsViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserPostsViewHolder.create(parent = parent)

    override fun onBindViewHolder(holder: UserPostsViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post) =
                oldItem == newItem
        }
    }
}