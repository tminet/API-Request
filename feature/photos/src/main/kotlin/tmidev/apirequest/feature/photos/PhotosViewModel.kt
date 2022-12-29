package tmidev.apirequest.feature.photos

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tmidev.apirequest.core.common.util.Constants.ALBUM_ID_KEY
import tmidev.apirequest.core.common.util.ResultType
import tmidev.apirequest.core.common.util.getStringRes
import tmidev.apirequest.core.domain.usecase.GetPhotosByAlbumIdUseCase
import javax.inject.Inject
import tmidev.apirequest.core.design.R as DesignR

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPhotosByAlbumIdUseCase: GetPhotosByAlbumIdUseCase
) : ViewModel() {
    private val _screenState: MutableStateFlow<PhotosScreenState> = MutableStateFlow(
        value = PhotosScreenState.Loading
    )

    val screenState: StateFlow<PhotosScreenState> = _screenState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = _screenState.value
    )

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            val albumId = savedStateHandle.get<Int>(ALBUM_ID_KEY)

            if (albumId == null) _screenState.update {
                PhotosScreenState.Error(stringRes = DesignR.string.unableToGetAlbumId)
            }
            else getPhotosByAlbumIdUseCase(albumId = albumId).collectLatest { result ->
                when (result) {
                    is ResultType.Loading -> _screenState.update {
                        PhotosScreenState.Loading
                    }
                    is ResultType.Success -> _screenState.update {
                        PhotosScreenState.Success(photos = result.data)
                    }
                    is ResultType.Error -> _screenState.update {
                        PhotosScreenState.Error(stringRes = result.errorType.getStringRes())
                    }
                }
            }
        }
    }

    fun reload() {
        getPhotos()
    }
}