package tmidev.apirequest.feature.users

import tmidev.apirequest.core.model.User

sealed interface UsersScreenState {
    object Loading : UsersScreenState

    data class Success(
        val users: List<User>
    ) : UsersScreenState

    data class Error(
        val stringRes: Int
    ) : UsersScreenState
}