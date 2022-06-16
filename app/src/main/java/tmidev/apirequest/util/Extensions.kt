package tmidev.apirequest.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import tmidev.apirequest.domain.type.ResultType

suspend fun <T> Flow<ResultType<T>>.collectResult(
    loading: suspend () -> Unit,
    success: suspend (data: T) -> Unit,
    error: suspend () -> Unit
) = collectLatest { result ->
    when (result) {
        is ResultType.Loading -> loading()
        is ResultType.Success -> success(result.data)
        is ResultType.Error -> error()
    }
}