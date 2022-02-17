package tmidev.apirequest.presentation.screen_user_posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tmidev.apirequest.R
import tmidev.apirequest.databinding.ItemUserPostBinding
import tmidev.apirequest.domain.model.Post

class UserPostsViewHolder(
    private val binding: ItemUserPostBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) = binding.run {
        textViewUserId.text = itemView.resources.getString(
            R.string.userId, post.userId
        )

        textViewPostId.text = itemView.resources.getString(
            R.string.postId, post.id
        )

        textViewPostTitle.text = post.title
        textViewPostBody.text = post.body
    }

    companion object {
        fun create(parent: ViewGroup): UserPostsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemUserPostBinding.inflate(inflater, parent, false)
            return UserPostsViewHolder(binding = itemBinding)
        }
    }
}