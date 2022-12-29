package tmidev.apirequest.feature.albums

import tmidev.apirequest.core.model.Album

sealed interface AlbumsScreenState {
    object Loading : AlbumsScreenState

    data class Success(
        val albums: List<Album>
    ) : AlbumsScreenState

    data class Error(
        val stringRes: Int
    ) : AlbumsScreenState
}