package tmidev.apirequest.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.domain.model.User
import tmidev.apirequest.domain.type.ResultType

interface GetUsersUseCase {
    operator fun invoke(): Flow<ResultType<List<User>>>
}