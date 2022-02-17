package tmidev.apirequest.data.remote.retrofit.response

import com.google.gson.annotations.SerializedName
import tmidev.apirequest.domain.model.User

data class UserResponseGson(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("address")
    val address: AddressResponse,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("website")
    val website: String
) {
    data class AddressResponse(
        @SerializedName("street")
        val street: String,
        @SerializedName("suite")
        val suite: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("zipcode")
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