package tmidev.apirequest.presentation.screen_users

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import tmidev.apirequest.domain.model.User
import tmidev.apirequest.util.UserAlbumsClick
import tmidev.apirequest.util.UserPostsClick

class UsersAdapter(
    private val userPostsClick: UserPostsClick,
    private val userAlbumsClick: UserAlbumsClick
) : ListAdapter<User, UsersViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UsersViewHolder.create(
            parent = parent,
            userPostsClick = userPostsClick,
            userAlbumsClick = userAlbumsClick
        )

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User) =
                oldItem == newItem
        }
    }
}