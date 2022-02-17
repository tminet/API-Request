package tmidev.apirequest.data.remote.ktor.response

import kotlinx.serialization.Serializable
import tmidev.apirequest.domain.model.Post

@Serializable
data class PostResponseKt(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) {
    fun toPost() = Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}
