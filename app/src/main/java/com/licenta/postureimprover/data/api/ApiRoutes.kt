package com.licenta.postureimprover.data.api

object ApiRoutes {
    private const val PORT = 5000
    private const val BASE_URL = "http://192.168.100.13:$PORT"
    const val REGISTER = "$BASE_URL/register"
    const val LOGIN = "$BASE_URL/login"

}