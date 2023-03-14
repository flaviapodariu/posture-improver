package com.licenta.postureimprover.data.api

object ApiRoutes {
    private const val PORT = 5000
    const val BASE_URL = "http://192.168.100.81:$PORT"
    const val REGISTER = "$BASE_URL/register"
    const val LOGIN = "$BASE_URL/login"
    const val DASHBOARD = "$BASE_URL/dashboard"

}