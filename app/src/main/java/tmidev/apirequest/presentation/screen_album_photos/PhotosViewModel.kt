package tmidev.apirequest.presentation.screen_album_photos

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tmidev.apirequest.R
import tmidev.apirequest.domain.usecase.GetAlbumPhotosUseCase
import tmidev.apirequest.util.collectResult
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val getAlbumPhotosUseCase: GetAlbumPhotosUseCase,
    savedState: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow<PhotosUiState>(PhotosUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val albumId = savedState.get<Int>("albumId")

    init {
        getAlbumPhotos()
    }

    private fun getAlbumPhotos() = viewModelScope.launch {
        if (albumId == null) _uiState.value = PhotosUiState.Error(
            message = R.string.somethingWentWrong
        ) else getAlbumPhotosUseCase(albumId = albumId).collectResult(
            loading = {
                _uiState.value = PhotosUiState.Loading
            },
            success = { photos ->
                _uiState.value = PhotosUiState.Success(photos = photos.toPhotosItem())
            },
            error = {
                _uiState.value = PhotosUiState.Error(message = R.string.somethingWentWrong)
            }
        )
    }

    fun reloadAlbumPhotos() = viewModelScope.launch {
        _uiState.value = PhotosUiState.Loading
        delay(timeMillis = 1000L)
        getAlbumPhotos()
    }
}