package tmidev.apirequest.core.network.retrofit.response

import com.google.gson.annotations.SerializedName

/**
 * Model class to represent a user from api.
 */
data class UserResponse(
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
    val website: String
)