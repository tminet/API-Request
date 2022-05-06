package tmidev.apirequest.presentation.screen_user_posts

import android.view.LayoutInflater
import android.view.ViewGroup
import tmidev.apirequest.R
import tmidev.apirequest.databinding.ItemPostBinding
import tmidev.apirequest.presentation.common.GenericViewHolder

class PostsViewHolder(
    binding: ItemPostBinding
) : GenericViewHolder<PostItem>(binding) {
    private val textUserId = binding.textViewUserId
    private val textPostId = binding.textViewPostId
    private val textPostTitle = binding.textViewPostTitle
    private val textPostBody = binding.textViewPostBody

    override fun bind(data: PostItem) {
        itemView.resources.run {
            textUserId.text = getString(R.string.userId, data.userId)
            textPostId.text = getString(R.string.postId, data.id)
        }

        textPostTitle.text = data.title
        textPostBody.text = data.body
    }

    companion object {
        fun create(parent: ViewGroup): PostsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPostBinding.inflate(inflater, parent, false)

            return PostsViewHolder(binding = itemBinding)
        }
    }
}