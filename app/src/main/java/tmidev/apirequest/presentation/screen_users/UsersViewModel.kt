package tmidev.apirequest.presentation.screen_users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tmidev.apirequest.R
import tmidev.apirequest.domain.type.ResultType
import tmidev.apirequest.domain.usecase.GetUsersUseCase
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UsersUiState>(UsersUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() = viewModelScope.launch {
        getUsersUseCase().collectLatest { resultType ->
            _uiState.value = when (resultType) {
                is ResultType.Loading -> UsersUiState.Loading
                is ResultType.Success -> UsersUiState.Success(
                    users = resultType.data
                )
                is ResultType.Error -> UsersUiState.Error(
                    message = R.string.somethingWentWrong
                )
            }
        }
    }

    fun reloadUsers() = getUsers()
}