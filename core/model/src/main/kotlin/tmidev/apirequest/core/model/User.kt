package tmidev.apirequest.core.model

/**
 * Model class to represent a user.
 */
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
)