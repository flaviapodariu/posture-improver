package com.licenta.postureimprover.di

import android.app.Application
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.LENS_FACING_BACK
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCameraProvider(app: Application): ProcessCameraProvider {
        return ProcessCameraProvider.getInstance(app).get()
    }

    @Provides
    @Singleton
    fun provideCameraSelector() : CameraSelector {
        return CameraSelector.Builder()
            .requireLensFacing(LENS_FACING_BACK)
            .build()
    }

    @Provides
    @Singleton
    fun provideCameraPreview(): Preview {
        return Preview.Builder()
            .build()
    }

}