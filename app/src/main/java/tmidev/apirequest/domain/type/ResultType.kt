package tmidev.apirequest.domain.type

sealed class ResultType<out T> {
    object Loading : ResultType<Nothing>()

    class Success<T>(val data: T) : ResultType<T>()

    object Error : ResultType<Nothing>()
}