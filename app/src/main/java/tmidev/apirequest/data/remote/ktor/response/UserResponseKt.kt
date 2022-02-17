package tmidev.apirequest.data.remote.ktor.response

import kotlinx.serialization.Serializable
import tmidev.apirequest.domain.model.User

@Serializable
data class UserResponseKt(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressResponse,
    val phone: String,
    val website: String
) {
    @Serializable
    data class AddressResponse(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String
    ) {
        fun toAddress() = User.Address(
            street = street,
            city = city,
            zipcode = zipcode
        )
    }

    fun toUser() = User(
        id = id,
        name = name,
        email = email,
        address = address.toAddress(),
        phone = phone,
        website = website
    )
}