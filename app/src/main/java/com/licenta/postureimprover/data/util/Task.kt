package com.licenta.postureimprover.data.util

sealed class Task<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T): Task<T>(data)
    class Loading<T>(data: T? = null): Task<T>(data)
    class Failure<T>(throwable: Throwable, data: T? = null) : Task<T>(data,throwable)
}


