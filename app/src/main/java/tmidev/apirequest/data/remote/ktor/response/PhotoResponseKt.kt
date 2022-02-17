package tmidev.apirequest.data.remote.ktor.response

import kotlinx.serialization.Serializable
import tmidev.apirequest.domain.model.Photo

@Serializable
data class PhotoResponseKt(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
) {
    fun toPhoto() = Photo(
        albumId = albumId,
        id = id,
        title = title,
        url = url
    )
}