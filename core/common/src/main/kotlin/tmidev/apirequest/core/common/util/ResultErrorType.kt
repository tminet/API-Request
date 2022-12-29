package tmidev.apirequest.core.common.util

import tmidev.apirequest.core.common.R
import java.net.UnknownHostException

enum class ResultErrorType {
    Connection, Unknown
}

/**
 * Convert an exception to [ResultErrorType].
 */
fun Throwable.asErrorType(): ResultErrorType = when (this) {
    is UnknownHostException -> ResultErrorType.Connection
    else -> ResultErrorType.Unknown
}

/**
 * Get a string resource reference according to the [ResultErrorType].
 */
fun ResultErrorType.getStringRes(): Int = when (this) {
    ResultErrorType.Connection -> R.string.unableToConnect
    ResultErrorType.Unknown -> R.string.somethingWentWrong
}