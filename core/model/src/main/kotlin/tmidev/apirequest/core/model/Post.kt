package tmidev.apirequest.core.model

/**
 * Model class to represent a post.
 */
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)