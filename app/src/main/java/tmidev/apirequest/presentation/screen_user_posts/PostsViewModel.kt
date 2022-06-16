package tmidev.apirequest.presentation.screen_user_posts

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tmidev.apirequest.R
import tmidev.apirequest.domain.usecase.GetUserPostsUseCase
import tmidev.apirequest.util.collectResult
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getUserPostsUseCase: GetUserPostsUseCase,
    savedState: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow<PostsUiState>(PostsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val userId = savedState.get<Int>("userId")

    init {
        getUserPosts()
    }

    private fun getUserPosts() = viewModelScope.launch {
        if (userId == null) _uiState.value = PostsUiState.Error(
            message = R.string.somethingWentWrong
        ) else getUserPostsUseCase(userId = userId).collectResult(
            loading = {
                _uiState.value = PostsUiState.Loading
            },
            success = { posts ->
                _uiState.value = PostsUiState.Success(posts = posts.toPostsItem())
            },
            error = {
                _uiState.value = PostsUiState.Error(message = R.string.somethingWentWrong)
            }
        )
    }

    fun reloadUserPosts() = viewModelScope.launch {
        _uiState.value = PostsUiState.Loading
        delay(timeMillis = 1000L)
        getUserPosts()
    }
}