package tmidev.apirequest.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.data.RepositoryJsonPlaceholder
import tmidev.apirequest.domain.model.Post
import tmidev.apirequest.domain.type.ResultType
import javax.inject.Inject

class GetUserPostsUseCaseImpl @Inject constructor(
    private val repository: RepositoryJsonPlaceholder
) : GetUserPostsUseCase {
    override fun invoke(userId: Int): Flow<ResultType<List<Post>>> =
        repository.getUserPosts(userId = userId)
}