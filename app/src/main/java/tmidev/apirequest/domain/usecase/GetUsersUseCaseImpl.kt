package tmidev.apirequest.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.data.RepositoryJsonPlaceholder
import tmidev.apirequest.domain.model.User
import tmidev.apirequest.domain.type.ResultType
import javax.inject.Inject

class GetUsersUseCaseImpl @Inject constructor(
    private val repository: RepositoryJsonPlaceholder
) : GetUsersUseCase {
    override fun invoke(): Flow<ResultType<List<User>>> = repository.getUsers()
}