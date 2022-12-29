package tmidev.apirequest.core.network.ktor.response

import kotlinx.serialization.Serializable

/**
 * Model class to represent a photo from api.
 */
@Serializable
data class PhotoResponse(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)