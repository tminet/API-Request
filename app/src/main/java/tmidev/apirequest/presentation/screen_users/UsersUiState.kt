package tmidev.apirequest.presentation.screen_users

import androidx.annotation.StringRes

sealed class UsersUiState {
    object Loading : UsersUiState()

    data class Success(val users: List<UserItem>) : UsersUiState()

    data class Error(@StringRes val message: Int) : UsersUiState()
}