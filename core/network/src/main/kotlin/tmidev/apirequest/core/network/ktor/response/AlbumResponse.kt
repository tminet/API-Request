package tmidev.apirequest.core.network.ktor.response

import kotlinx.serialization.Serializable

/**
 * Model class to represent an album from api.
 */
@Serializable
data class AlbumResponse(
    val userId: Int,
    val id: Int,
    val title: String
)