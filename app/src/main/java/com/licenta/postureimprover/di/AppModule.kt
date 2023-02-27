package com.licenta.postureimprover.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.LENS_FACING_FRONT
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.google.api.Http
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.licenta.postureimprover.data.api.AuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideDatabase() : FirebaseFirestore = Firebase.firestore

//    CAMERA PREVIEW
    @Provides
    @Singleton
    fun provideCameraProvider(app: Application): ProcessCameraProvider {
        return ProcessCameraProvider.getInstance(app).get()
    }

    @Provides
    @Singleton
    fun provideCameraSelector() : CameraSelector {
        return CameraSelector.Builder()
            .requireLensFacing(LENS_FACING_FRONT)
            .build()
    }

    @Provides
    @Singleton
    fun provideCameraPreview(): Preview {
        return Preview.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideCameraExecutor(): ExecutorService {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Singleton
    fun provideAuthService(client: HttpClient, preferences: SharedPreferences) : AuthenticationService {
        return AuthenticationService(client, preferences)
    }

    @Provides
    @Singleton
    fun provideHttpClient() : HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
                filter { request ->
                    request.url.host.contains("ktor.io")
                }
            }
            install(ContentNegotiation) {
                json()
            }
        }
    }
    @Provides
    @Singleton
    fun providePreferences(app: Application) : SharedPreferences {
        return app.getSharedPreferences("preferences", MODE_PRIVATE)
    }

}