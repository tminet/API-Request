package tmidev.apirequest.presentation.screen_users

import android.view.LayoutInflater
import android.view.ViewGroup
import tmidev.apirequest.R
import tmidev.apirequest.databinding.ItemUserBinding
import tmidev.apirequest.presentation.common.GenericViewHolder
import tmidev.apirequest.util.UserAlbumsClick
import tmidev.apirequest.util.UserPostsClick

class UsersViewHolder(
    binding: ItemUserBinding,
    private val userPostsClick: UserPostsClick,
    private val userAlbumsClick: UserAlbumsClick,
) : GenericViewHolder<UserItem>(binding) {
    private val textUserId = binding.textViewUserId
    private val textUserRealName = binding.textViewUserRealName
    private val textUserEmail = binding.textViewUserEmail
    private val textUserPhone = binding.textViewUserPhone
    private val textUserWebsite = binding.textViewUserWebsite
    private val textUserZipCode = binding.textViewUserZipCode
    private val textUserCity = binding.textViewUserCity
    private val textUserStreet = binding.textViewUserStreet
    private val buttonUserPosts = binding.buttonUserPosts
    private val buttonUserAlbums = binding.buttonUserAlbums

    override fun bind(data: UserItem) {
        textUserRealName.text = data.name

        itemView.resources.run {
            textUserId.text = getString(R.string.userId, data.id)
            textUserEmail.text = getString(R.string.email, data.email)
            textUserPhone.text = getString(R.string.phone, data.phone)
            textUserWebsite.text = getString(R.string.website, data.website)
            textUserZipCode.text = getString(R.string.zipCode, data.address.zipcode)
            textUserCity.text = getString(R.string.city, data.address.city)
            textUserStreet.text = getString(R.string.street, data.address.street)
        }

        buttonUserPosts.setOnClickListener {
            userPostsClick.invoke(data.id)
        }

        buttonUserAlbums.setOnClickListener {
            userAlbumsClick.invoke(data.id)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            userPostsClick: UserPostsClick,
            userAlbumsClick: UserAlbumsClick
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