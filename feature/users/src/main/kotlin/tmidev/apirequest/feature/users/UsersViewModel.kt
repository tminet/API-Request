package tmidev.apirequest.feature.users

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
import tmidev.apirequest.core.common.util.ResultType
import tmidev.apirequest.core.common.util.getStringRes
import tmidev.apirequest.core.domain.usecase.GetUsersUseCase
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    private val _screenState: MutableStateFlow<UsersScreenState> = MutableStateFlow(
        value = UsersScreenState.Loading
    )

    val screenState: StateFlow<UsersScreenState> = _screenState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = _screenState.value
    )

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase().collectLatest { result ->
                when (result) {
                    is ResultType.Loading -> _screenState.update {
                        UsersScreenState.Loading
                    }
                    is ResultType.Success -> _screenState.update {
                        UsersScreenState.Success(users = result.data)
                    }
                    is ResultType.Error -> _screenState.update {
                        UsersScreenState.Error(stringRes = result.errorType.getStringRes())
                    }
                }
            }
        }
    }

    fun reload() {
        getUsers()
    }
}