package tmidev.apirequest.core.network.ktor.response

import kotlinx.serialization.Serializable

/**
 * Model class to represent a user from api.
 */
@Serializable
data class UserResponse(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
)