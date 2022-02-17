package tmidev.apirequest.presentation.screen_users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tmidev.apirequest.R
import tmidev.apirequest.databinding.ItemUserBinding
import tmidev.apirequest.domain.model.User
import tmidev.apirequest.util.UserAlbumsClick
import tmidev.apirequest.util.UserPostsClick

class UsersViewHolder(
    private val binding: ItemUserBinding,
    private val userPostsClick: UserPostsClick?,
    private val userAlbumsClick: UserAlbumsClick?,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User) = binding.run {
        textViewUserId.text = itemView.resources.getString(
            R.string.userId, user.id
        )

        textViewUserRealName.text = user.name

        textViewUserEmail.text = itemView.resources.getString(
            R.string.email, user.email
        )

        textViewUserPhone.text = itemView.resources.getString(
            R.string.phone, user.phone
        )

        textViewUserWebsite.text = itemView.resources.getString(
            R.string.website, user.website
        )

        textViewUserZipCode.text = itemView.resources.getString(
            R.string.zipCode, user.address.zipcode
        )

        textViewUserCity.text = itemView.resources.getString(
            R.string.city, user.address.city
        )

        textViewUserStreet.text = itemView.resources.getString(
            R.string.street, user.address.street
        )

        buttonUserPosts.setOnClickListener {
            userPostsClick?.invoke(user.id)
        }

        buttonUserAlbums.setOnClickListener {
            userAlbumsClick?.invoke(user.id)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            userPostsClick: UserPostsClick?,
            userAlbumsClick: UserAlbumsClick?
        ): UsersViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemUserBinding.inflate(inflater, parent, false)
            return UsersViewHolder(
                binding = itemBinding,
                userPostsClick = userPostsClick,
                userAlbumsClick = userAlbumsClick
            )
        }
    }
}