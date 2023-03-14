package com.licenta.postureimprover.data.api.services

import android.content.SharedPreferences
import com.licenta.postureimprover.data.api.ApiRoutes
import com.licenta.postureimprover.data.api.dto.AuthRes
import com.licenta.postureimprover.data.api.dto.LoginReq
import com.licenta.postureimprover.data.api.dto.RegisterReq
import com.licenta.postureimprover.data.util.AuthResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val client: HttpClient,
) : AuthService  {
    override suspend fun login(loginReq: LoginReq) : AuthResponse<AuthRes>{
        return try {
            val res: AuthRes = client.post {
                url(ApiRoutes.LOGIN)
                contentType(ContentType.Application.Json)
                setBody(loginReq)
            }.body()

            AuthResponse.Success(res)

        }
        catch(e: ClientRequestException) {
            e.printStackTrace()
            AuthResponse.Failure(e)
        }

        catch(e: Exception) {
            e.printStackTrace()
            AuthResponse.Failure(e)

        }
    }
    override suspend fun register(registerUser: RegisterReq): AuthResponse<AuthRes> {
        return try {
            val res: AuthRes = client.post{
                url(ApiRoutes.REGISTER)
                contentType(ContentType.Application.Json)
                setBody(registerUser)
            }.body()   //content negotiation deserializes response body

            AuthResponse.Success(res)

        }
        catch(e:RedirectResponseException) {
            e.printStackTrace()
            AuthResponse.Failure(e)

        }
        catch(e:ClientRequestException) {
            e.printStackTrace()
            AuthResponse.Failure(e)

        }
        catch(e:ServerResponseException) {
            e.printStackTrace()
            AuthResponse.Failure(e)
        }
    }

}