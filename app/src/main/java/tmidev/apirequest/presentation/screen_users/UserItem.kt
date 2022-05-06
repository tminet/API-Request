package tmidev.apirequest.presentation.screen_users

import tmidev.apirequest.domain.model.User
import tmidev.apirequest.presentation.common.GenericListItem

data class UserItem(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val website: String,
    val address: User.Address,
    override val key: Long = id.toLong()
) : GenericListItem

fun List<User>.toUsersItem() = map { user ->
    UserItem(
        id = user.id,
        name = user.name,
        email = user.email,
        phone = user.phone,
        website = user.website,
        address = user.address
    )
}