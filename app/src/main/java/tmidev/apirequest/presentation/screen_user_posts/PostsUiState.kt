package tmidev.apirequest.presentation.screen_user_posts

import androidx.annotation.StringRes

sealed class PostsUiState {
    object Loading : PostsUiState()

    data class Success(val posts: List<PostItem>) : PostsUiState()

    data class Error(@StringRes val message: Int) : PostsUiState()
}