package tmidev.apirequest.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import tmidev.apirequest.core.common.coroutines.CoroutinesDispatchers
import tmidev.apirequest.core.common.util.ResultType
import tmidev.apirequest.core.common.util.asErrorType
import tmidev.apirequest.core.model.User
import tmidev.apirequest.core.network.ktor.JsonPlaceholderApiKtor
import tmidev.apirequest.core.network.retrofit.JsonPlaceholderApiRetrofit
import javax.inject.Inject
import tmidev.apirequest.core.network.ktor.response.UserResponse as UserResponseKt
import tmidev.apirequest.core.network.retrofit.response.UserResponse as UserResponseGs

interface UsersRepository {
    fun getUsers(): Flow<ResultType<List<User>>>

    fun getUserById(userId: Int): Flow<ResultType<User>>
}

class UsersRepositoryImplKtor @Inject constructor(
    private val jsonPlaceholderApiKtor: JsonPlaceholderApiKtor,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : UsersRepository {
    override fun getUsers(): Flow<ResultType<List<User>>> = flow<ResultType<List<User>>> {
        val users = jsonPlaceholderApiKtor.getUsers().map { it.asUser() }
        emit(value = ResultType.Success(data = users))
    }.flowOn(context = coroutinesDispatchers.io).onStart {
        emit(value = ResultType.Loading)
    }.catch { throwable ->
        throwable.printStackTrace()
        emit(value = ResultType.Error(errorType = throwable.asErrorType()))
    }

    override fun getUserById(userId: Int): Flow<ResultType<User>> = flow<ResultType<User>> {
        val user = jsonPlaceholderApiKtor.getUserById(userId = userId).asUser()
        emit(value = ResultType.Success(data = user))
    }.flowOn(context = coroutinesDispatchers.io).onStart {
        emit(value = ResultType.Loading)
    }.catch { throwable ->
        throwable.printStackTrace()
        emit(value = ResultType.Error(errorType = throwable.asErrorType()))
    }

    private fun UserResponseKt.asUser() = User(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website
    )
}

class UsersRepositoryImplRetrofit @Inject constructor(
    private val jsonPlaceholderApiRetrofit: JsonPlaceholderApiRetrofit,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : UsersRepository {
    override fun getUsers(): Flow<ResultType<List<User>>> = flow<ResultType<List<User>>> {
        val users = jsonPlaceholderApiRetrofit.getUsers().map { it.asUser() }
        emit(value = ResultType.Success(data = users))
    }.flowOn(context = coroutinesDispatchers.io).onStart {
        emit(value = ResultType.Loading)
    }.catch { throwable ->
        throwable.printStackTrace()
        emit(value = ResultType.Error(errorType = throwable.asErrorType()))
    }

    override fun getUserById(userId: Int): Flow<ResultType<User>> = flow<ResultType<User>> {
        val user = jsonPlaceholderApiRetrofit.getUserById(userId = userId).asUser()
        emit(value = ResultType.Success(data = user))
    }.flowOn(context = coroutinesDispatchers.io).onStart {
        emit(value = ResultType.Loading)
    }.catch { throwable ->
        throwable.printStackTrace()
        emit(value = ResultType.Error(errorType = throwable.asErrorType()))
    }

    private fun UserResponseGs.asUser() = User(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website
    )
}