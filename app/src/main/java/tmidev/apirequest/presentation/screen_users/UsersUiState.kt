package tmidev.apirequest.presentation.screen_users

import androidx.annotation.StringRes
import tmidev.apirequest.domain.model.User

sealed class UsersUiState {
    object Loading : UsersUiState()

    data class Success(val users: List<User>) : UsersUiState()

    data class Error(@StringRes val message: Int) : UsersUiState()
}