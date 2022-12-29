package tmidev.apirequest.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.core.common.util.ResultType
import tmidev.apirequest.core.data.di.KtorImplementation
import tmidev.apirequest.core.data.di.RetrofitImplementation
import tmidev.apirequest.core.data.repository.UsersRepository
import tmidev.apirequest.core.model.User
import javax.inject.Inject

interface GetUserByIdUseCase {
    operator fun invoke(userId: Int): Flow<ResultType<User>>
}

class GetUserByIdUseCaseImpl @Inject constructor(
    @KtorImplementation private val usersRepository: UsersRepository
//    @RetrofitImplementation private val usersRepository: UsersRepository
) : GetUserByIdUseCase {
    override fun invoke(userId: Int): Flow<ResultType<User>> =
        usersRepository.getUserById(userId = userId)
}