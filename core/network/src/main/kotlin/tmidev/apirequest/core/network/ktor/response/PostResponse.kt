package tmidev.apirequest.core.network.ktor.response

import kotlinx.serialization.Serializable

/**
 * Model class to represent a post from api.
 */
@Serializable
data class PostResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)