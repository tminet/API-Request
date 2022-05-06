package tmidev.apirequest.presentation.screen_album_photos

import androidx.annotation.StringRes

sealed class PhotosUiState {
    object Loading : PhotosUiState()

    data class Success(val photos: List<PhotoItem>) : PhotosUiState()

    data class Error(@StringRes val message: Int) : PhotosUiState()
}