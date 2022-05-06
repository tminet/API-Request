package tmidev.apirequest.presentation.screen_album_photos

import tmidev.apirequest.domain.model.Photo
import tmidev.apirequest.presentation.common.GenericListItem

data class PhotoItem(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    override val key: Long = id.toLong()
) : GenericListItem

fun List<Photo>.toPhotosItem() = map { photo ->
    PhotoItem(
        albumId = photo.albumId,
        id = photo.id,
        title = photo.title,
        url = photo.url
    )
}