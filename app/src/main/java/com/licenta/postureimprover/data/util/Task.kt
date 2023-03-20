package com.licenta.postureimprover.data.util

sealed class Task<out R> {
    data class Success<out R>(val result: R): Task<R>()
    data class Failure(val exception: Exception): Task<Nothing>()
    object Loading: Task<Nothing>()
}
