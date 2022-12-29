package tmidev.apirequest.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import tmidev.apirequest.core.common.coroutines.CoroutinesDispatchers
import tmidev.apirequest.core.common.util.ResultType
import tmidev.apirequest.core.common.util.asErrorType
import tmidev.apirequest.core.model.Post
import tmidev.apirequest.core.network.ktor.JsonPlaceholderApiKtor
import tmidev.apirequest.core.network.retrofit.JsonPlaceholderApiRetrofit
import javax.inject.Inject
import tmidev.apirequest.core.network.ktor.response.PostResponse as PostResponseKt
import tmidev.apirequest.core.network.retrofit.response.PostResponse as PostResponseGs

interface PostsRepository {
    fun getPostsByUserId(
        userId: Int
    ): Flow<ResultType<List<Post>>>
}

class PostsRepositoryImplKtor @Inject constructor(
    private val jsonPlaceholderApiKtor: JsonPlaceholderApiKtor,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : PostsRepository {
    override fun getPostsByUserId(
        userId: Int
    ): Flow<ResultType<List<Post>>> = flow<ResultType<List<Post>>> {
        val posts = jsonPlaceholderApiKtor.getPostsByUserId(
            userId = userId
        ).map { it.asPost() }
        emit(value = ResultType.Success(data = posts))
    }.flowOn(context = coroutinesDispatchers.io).onStart {
        emit(value = ResultType.Loading)
    }.catch { throwable ->
        throwable.printStackTrace()
        emit(value = ResultType.Error(errorType = throwable.asErrorType()))
    }

    private fun PostResponseKt.asPost() = Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}

class PostsRepositoryImplRetrofit @Inject constructor(
    private val jsonPlaceholderApiRetrofit: JsonPlaceholderApiRetrofit,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : PostsRepository {
    override fun getPostsByUserId(
        userId: Int
    ): Flow<ResultType<List<Post>>> = flow<ResultType<List<Post>>> {
        val posts = jsonPlaceholderApiRetrofit.getPostsByUserId(
            userId = userId
        ).map { it.asPost() }
        emit(value = ResultType.Success(data = posts))
    }.flowOn(context = coroutinesDispatchers.io).onStart {
        emit(value = ResultType.Loading)
    }.catch { throwable ->
        throwable.printStackTrace()
        emit(value = ResultType.Error(errorType = throwable.asErrorType()))
    }

    private fun PostResponseGs.asPost() = Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}