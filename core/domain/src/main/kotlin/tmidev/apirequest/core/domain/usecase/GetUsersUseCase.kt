package tmidev.apirequest.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.core.common.util.ResultType
import tmidev.apirequest.core.data.di.KtorImplementation
import tmidev.apirequest.core.data.di.RetrofitImplementation
import tmidev.apirequest.core.data.repository.UsersRepository
import tmidev.apirequest.core.model.User
import javax.inject.Inject

interface GetUsersUseCase {
    operator fun invoke(): Flow<ResultType<List<User>>>
}

class GetUsersUseCaseImpl @Inject constructor(
    @KtorImplementation private val usersRepository: UsersRepository
//    @RetrofitImplementation private val usersRepository: UsersRepository
) : GetUsersUseCase {
    override fun invoke(): Flow<ResultType<List<User>>> =
        usersRepository.getUsers()
}