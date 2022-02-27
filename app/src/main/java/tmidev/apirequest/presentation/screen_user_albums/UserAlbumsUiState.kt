package tmidev.apirequest.presentation.screen_user_albums

import androidx.annotation.StringRes
import tmidev.apirequest.domain.model.Album

sealed class UserAlbumsUiState {
    object Loading : UserAlbumsUiState()

    data class Success(val albums: List<Album>) : UserAlbumsUiState()

    data class Error(@StringRes val message: Int) : UserAlbumsUiState()
}