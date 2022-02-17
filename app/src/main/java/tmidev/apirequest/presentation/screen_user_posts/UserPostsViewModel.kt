package tmidev.apirequest.presentation.screen_user_posts

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tmidev.apirequest.R
import tmidev.apirequest.domain.type.ResultType
import tmidev.apirequest.domain.usecase.GetUserPostsUseCase
import javax.inject.Inject

@HiltViewModel
class UserPostsViewModel @Inject constructor(
    private val getUserPostsUseCase: GetUserPostsUseCase,
    savedState: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow<UserPostsUiState>(UserPostsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val userId = savedState.get<Int>("userId")

    init {
        getUserPosts()
    }

    private fun getUserPosts() = viewModelScope.launch {
        if (userId == null) _uiState.value = UserPostsUiState.Error(
            message = R.string.somethingWentWrong
        ) else getUserPostsUseCase(userId = userId).collectLatest { resultType ->
            _uiState.value = when (resultType) {
                is ResultType.Loading -> UserPostsUiState.Loading
                is ResultType.Success -> UserPostsUiState.Success(
                    posts = resultType.data
                )
                is ResultType.Error -> UserPostsUiState.Error(
                    message = R.string.somethingWentWrong
                )
            }
        }
    }

    fun reloadUserPosts() = viewModelScope.launch {
        _uiState.value = UserPostsUiState.Loading
        delay(timeMillis = 1000L)
        getUserPosts()
    }
}