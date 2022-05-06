package tmidev.apirequest.presentation.screen_user_albums

import androidx.annotation.StringRes

sealed class AlbumsUiState {
    object Loading : AlbumsUiState()

    data class Success(val albums: List<AlbumItem>) : AlbumsUiState()

    data class Error(@StringRes val message: Int) : AlbumsUiState()
}