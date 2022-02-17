package tmidev.apirequest.presentation.screen_user_posts

import androidx.annotation.StringRes
import tmidev.apirequest.domain.model.Post

sealed class UserPostsUiState {
    object Loading : UserPostsUiState()

    data class Success(val posts: List<Post>) : UserPostsUiState()

    data class Error(@StringRes val message: Int) : UserPostsUiState()
}
