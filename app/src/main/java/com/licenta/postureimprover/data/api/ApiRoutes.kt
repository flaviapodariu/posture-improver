package com.licenta.postureimprover.data.api

object ApiRoutes {
    private const val PORT = 5000
//    const val BASE_URL = "https://posture-improver-ktor-production.up.railway.app"
    const val BASE_URL = "http://192.168.55.237:$PORT"
    const val REGISTER = "$BASE_URL/register"
    const val LOGIN = "$BASE_URL/login"
    const val DASHBOARD = "$BASE_URL/dashboard"
    const val DASH_VISITOR = "$DASHBOARD/visitor"
    const val WORKOUT = "$BASE_URL/workout"
    const val EXERCISES = "$BASE_URL/exercises"

}