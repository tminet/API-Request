package tmidev.apirequest.presentation.screen_user_albums

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tmidev.apirequest.R
import tmidev.apirequest.domain.usecase.GetUserAlbumsUseCase
import tmidev.apirequest.util.collectResult
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val getUserAlbumsUseCase: GetUserAlbumsUseCase,
    savedState: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow<AlbumsUiState>(AlbumsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val userId = savedState.get<Int>("userId")

    init {
        getUserAlbums()
    }

    private fun getUserAlbums() = viewModelScope.launch {
        if (userId == null) _uiState.value = AlbumsUiState.Error(
            message = R.string.somethingWentWrong
        ) else getUserAlbumsUseCase(userId = userId).collectResult(
            loading = {
                _uiState.value = AlbumsUiState.Loading
            },
            success = { albums ->
                _uiState.value = AlbumsUiState.Success(albums = albums.toAlbumsItem())
            },
            error = {
                _uiState.value = AlbumsUiState.Error(message = R.string.somethingWentWrong)
            }
        )
    }

    fun reloadUserAlbums() = viewModelScope.launch {
        _uiState.value = AlbumsUiState.Loading
        delay(timeMillis = 1000L)
        getUserAlbums()
    }
}