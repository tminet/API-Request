package tmidev.apirequest.presentation.screen_album_photos

import androidx.annotation.StringRes
import tmidev.apirequest.domain.model.Photo

sealed class AlbumPhotosUiState {
    object Loading : AlbumPhotosUiState()

    data class Success(val photos: List<Photo>) : AlbumPhotosUiState()

    data class Error(@StringRes val message: Int) : AlbumPhotosUiState()
}
