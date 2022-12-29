package tmidev.apirequest.feature.albums

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
import tmidev.apirequest.core.common.util.Constants
import tmidev.apirequest.core.common.util.ResultType
import tmidev.apirequest.core.common.util.getStringRes
import tmidev.apirequest.core.domain.usecase.GetAlbumsByUserIdUseCase
import javax.inject.Inject
import tmidev.apirequest.core.design.R as DesignR

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getAlbumsByUserIdUseCase: GetAlbumsByUserIdUseCase
) : ViewModel() {
    private val _screenState: MutableStateFlow<AlbumsScreenState> = MutableStateFlow(
        value = AlbumsScreenState.Loading
    )

    val screenState: StateFlow<AlbumsScreenState> = _screenState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = _screenState.value
    )

    init {
        getAlbums()
    }

    private fun getAlbums() {
        viewModelScope.launch {
            val userId = savedStateHandle.get<Int>(Constants.USER_ID_KEY)

            if (userId == null) _screenState.update {
                AlbumsScreenState.Error(stringRes = DesignR.string.unableToGetUserId)
            }
            else getAlbumsByUserIdUseCase(userId = userId).collectLatest { result ->
                when (result) {
                    is ResultType.Loading -> _screenState.update {
                        AlbumsScreenState.Loading
                    }
                    is ResultType.Success -> _screenState.update {
                        AlbumsScreenState.Success(albums = result.data)
                    }
                    is ResultType.Error -> _screenState.update {
                        AlbumsScreenState.Error(stringRes = result.errorType.getStringRes())
                    }
                }
            }
        }
    }

    fun reload() {
        getAlbums()
    }
}