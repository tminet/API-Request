package tmidev.apirequest.core.model

/**
 * Model class to represent a photo.
 */
data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String
)