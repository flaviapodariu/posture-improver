package com.licenta.postureimprover.data.util

import kotlinx.coroutines.flow.*

fun <ResultType, RequestType> networkBoundTask(
    query: () -> Flow<ResultType>,
    fetch: suspend () -> RequestType,
    saveFetchResult: suspend (RequestType) -> Unit,
    shouldFetch: (ResultType) -> Boolean = { true }
) = flow {

    val data = query().first()
    val flow = if(shouldFetch(data)) {
        emit(Task.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { Task.Success(it) }
        }
        catch(throwable: Throwable) {
            query().map { Task.Failure(throwable, it) }
        }
    }
    else {
        query().map { Task.Success(it) }
    }

    emitAll(flow)
}