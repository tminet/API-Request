package tmidev.apirequest.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import tmidev.apirequest.core.common.util.ResultType
import tmidev.apirequest.core.data.di.KtorImplementation
import tmidev.apirequest.core.data.di.RetrofitImplementation
import tmidev.apirequest.core.data.repository.PostsRepository
import tmidev.apirequest.core.model.Post
import javax.inject.Inject

interface GetPostsByUserIdUseCase {
    operator fun invoke(userId: Int): Flow<ResultType<List<Post>>>
}

class GetPostsByUserIdUseCaseImpl @Inject constructor(
    @KtorImplementation private val postsRepository: PostsRepository
//    @RetrofitImplementation private val postsRepository: PostsRepository
) : GetPostsByUserIdUseCase {
    override fun invoke(userId: Int): Flow<ResultType<List<Post>>> =
        postsRepository.getPostsByUserId(userId = userId)
}