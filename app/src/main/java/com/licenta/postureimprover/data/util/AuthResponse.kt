package com.licenta.postureimprover.data.util

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

sealed class AuthResponse<out R> {
    data class Success<out R>(val result: R): AuthResponse<R>()
    data class UserCollision(val exception: FirebaseAuthUserCollisionException): AuthResponse<Nothing>()
    data class InvalidCredentials(val exception: FirebaseAuthInvalidCredentialsException): AuthResponse<Nothing>()
    data class InvalidUser(val exception: FirebaseAuthInvalidUserException): AuthResponse<Nothing>()
    data class WeakPassword(val reason: String): AuthResponse<Nothing>()
    data class Failure(val exception: Exception): AuthResponse<Nothing>()
    object Loading: AuthResponse<Nothing>()
}
