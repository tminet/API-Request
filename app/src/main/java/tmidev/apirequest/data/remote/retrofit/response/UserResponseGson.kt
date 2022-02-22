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
    @SerializedName("phone")
    val phone: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("address")
    val address: AddressResponseGson,
) {
    data class AddressResponseGson(
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
        phone = phone,
        website = website,
        address = address.toAddress()
    )
}