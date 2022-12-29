package tmidev.apirequest.core.common.util

interface ResultType<out T> {
    object Loading : ResultType<Nothing>

    data class Success<T>(
        val data: T
    ) : ResultType<T>

    data class Error(
        val errorType: ResultErrorType
    ) : ResultType<Nothing>
}