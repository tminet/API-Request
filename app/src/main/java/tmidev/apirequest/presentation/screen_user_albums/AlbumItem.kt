package tmidev.apirequest.presentation.screen_user_albums

import tmidev.apirequest.domain.model.Album
import tmidev.apirequest.presentation.common.GenericListItem

data class AlbumItem(
    val userId: Int,
    val id: Int,
    val title: String,
    override val key: Long = id.toLong()
) : GenericListItem

fun List<Album>.toAlbumsItem() = map { album ->
    AlbumItem(
        userId = album.userId,
        id = album.id,
        title = album.title,
    )
}