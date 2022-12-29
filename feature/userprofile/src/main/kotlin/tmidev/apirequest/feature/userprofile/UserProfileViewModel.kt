package tmidev.apirequest.feature.userprofile

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
import tmidev.apirequest.core.common.util.Constants.USER_ID_KEY
import tmidev.apirequest.core.common.util.ResultType
import tmidev.apirequest.core.common.util.getStringRes
import tmidev.apirequest.core.domain.usecase.GetUserByIdUseCase
import javax.inject.Inject
import tmidev.apirequest.core.design.R as DesignR

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {
    private val _screenState: MutableStateFlow<UserProfileScreenState> = MutableStateFlow(
        value = UserProfileScreenState.Loading
    )

    val screenState: StateFlow<UserProfileScreenState> = _screenState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = _screenState.value
    )

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            val userId = savedStateHandle.get<Int>(USER_ID_KEY)

            if (userId == null) _screenState.update {
                UserProfileScreenState.Error(stringRes = DesignR.string.unableToGetUserId)
            }
            else getUserByIdUseCase(userId = userId).collectLatest { result ->
                when (result) {
                    is ResultType.Loading -> _screenState.update {
                        UserProfileScreenState.Loading
                    }
                    is ResultType.Success -> _screenState.update {
                        UserProfileScreenState.Success(user = result.data)
                    }
                    is ResultType.Error -> _screenState.update {
                        UserProfileScreenState.Error(stringRes = result.errorType.getStringRes())
                    }
                }
            }
        }
    }

    fun reload() {
        getUser()
    }
}