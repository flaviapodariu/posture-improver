package com.licenta.postureimprover.data.api.services

import com.licenta.postureimprover.data.api.ApiRoutes
import com.licenta.postureimprover.data.api.dto.response.AuthRes
import com.licenta.postureimprover.data.api.dto.request.LoginReq
import com.licenta.postureimprover.data.api.dto.request.RegisterReq
import com.licenta.postureimprover.data.util.Task
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val client: HttpClient,
) : AuthService  {
    override suspend fun login(loginReq: LoginReq) : Task<AuthRes>{
        return try {
            val res: AuthRes = client.post {
                url(ApiRoutes.LOGIN)
                contentType(ContentType.Application.Json)
                setBody(loginReq)
            }.body()

            Task.Success(res)

        }
        catch(e: ClientRequestException) {
            e.printStackTrace()
            Task.Failure(e)
        }

        catch(e: Exception) {
            e.printStackTrace()
            Task.Failure(e)

        }
    }
    override suspend fun register(registerUser: RegisterReq): Task<AuthRes> {
        return try {
            val res: AuthRes = client.post{
                url(ApiRoutes.REGISTER)
                contentType(ContentType.Application.Json)
                setBody(registerUser)
            }.body()   //content negotiation deserializes response body

            Task.Success(res)

        }
        catch(e:RedirectResponseException) {
            e.printStackTrace()
            Task.Failure(e)

        }
        catch(e:ClientRequestException) {
            e.printStackTrace()
            Task.Failure(e)

        }
        catch(e:ServerResponseException) {
            e.printStackTrace()
            Task.Failure(e)
        }
    }

}