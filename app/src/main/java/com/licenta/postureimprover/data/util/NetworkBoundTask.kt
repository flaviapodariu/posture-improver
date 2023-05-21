package com.licenta.postureimprover.data.util

import com.licenta.postureimprover.screens.viewmodels.AuthenticationViewModel.Companion.USER_ID
import io.ktor.client.engine.KTOR_DEFAULT_USER_AGENT
import kotlinx.coroutines.flow.*

fun <ResultType, RequestType> networkBoundTask(
    query: () -> Flow<ResultType>,
    fetch: suspend () -> RequestType,
    saveFetchResult: suspend (RequestType) -> Unit,
    shouldFetch: (ResultType) -> Boolean = { USER_ID != 0 }
) = flow {

    val data = query().first()
    val flow = if(shouldFetch(data)) {
        emit(Task.Loading(data))
        try {
            val f = fetch()
            saveFetchResult(f)
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