package com.licenta.postureimprover.di

import android.app.Application
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.LENS_FACING_FRONT
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.room.Room
import com.licenta.postureimprover.data.local.PostureDatabase
import com.licenta.postureimprover.data.local.PostureDatabase.Companion.migrationFrom6To7
import com.licenta.postureimprover.data.local.dao.CapturesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
            install(HttpTimeout)
        }
    }

    @Provides
    @Singleton
    fun provideRoomDb(app: Application) : PostureDatabase {
        return Room.databaseBuilder(app, PostureDatabase::class.java,"posture_database").addMigrations(migrationFrom6To7)
            .build()
    }


}