package tmidev.apirequest.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.domain.model.Post
import tmidev.apirequest.domain.type.ResultType

interface GetUserPostsUseCase {
    operator fun invoke(userId: Int): Flow<ResultType<List<Post>>>
}