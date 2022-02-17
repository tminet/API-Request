package tmidev.apirequest.data.remote.ktor.response

import kotlinx.serialization.Serializable
import tmidev.apirequest.domain.model.Album

@Serializable
data class AlbumResponseKt(
    val userId: Int,
    val id: Int,
    val title: String
) {
    fun toAlbum() = Album(
        userId = userId,
        id = id,
        title = title
    )
}