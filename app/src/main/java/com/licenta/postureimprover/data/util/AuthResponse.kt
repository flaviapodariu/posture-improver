package com.licenta.postureimprover.data.util

sealed class AuthResponse<out R> {
    data class Success<out R>(val result: R): AuthResponse<R>()
    data class Failure(val exception: Exception): AuthResponse<Nothing>()
    object Loading: AuthResponse<Nothing>()
}
