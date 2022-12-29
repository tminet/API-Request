package tmidev.apirequest.feature.userprofile

import tmidev.apirequest.core.model.User

sealed interface UserProfileScreenState {
    object Loading : UserProfileScreenState

    data class Success(
        val user: User
    ) : UserProfileScreenState

    data class Error(
        val stringRes: Int
    ) : UserProfileScreenState
}