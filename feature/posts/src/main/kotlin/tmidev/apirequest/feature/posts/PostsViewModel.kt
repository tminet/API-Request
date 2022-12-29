package tmidev.apirequest.feature.posts

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
import tmidev.apirequest.core.domain.usecase.GetPostsByUserIdUseCase
import javax.inject.Inject
import tmidev.apirequest.core.design.R as DesignR

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPostsByUserIdUseCase: GetPostsByUserIdUseCase
) : ViewModel() {
    private val _screenState: MutableStateFlow<PostsScreenState> = MutableStateFlow(
        value = PostsScreenState.Loading
    )

    val screenState: StateFlow<PostsScreenState> = _screenState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = _screenState.value
    )

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            val userId = savedStateHandle.get<Int>(Constants.USER_ID_KEY)

            if (userId == null) _screenState.update {
                PostsScreenState.Error(stringRes = DesignR.string.unableToGetUserId)
            }
            else getPostsByUserIdUseCase(userId = userId).collectLatest { result ->
                when (result) {
                    is ResultType.Loading -> _screenState.update {
                        PostsScreenState.Loading
                    }
                    is ResultType.Success -> _screenState.update {
                        PostsScreenState.Success(posts = result.data)
                    }
                    is ResultType.Error -> _screenState.update {
                        PostsScreenState.Error(stringRes = result.errorType.getStringRes())
                    }
                }
            }
        }
    }

    fun reload() {
        getPosts()
    }
}