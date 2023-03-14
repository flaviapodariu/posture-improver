package com.licenta.postureimprover.di

import com.licenta.postureimprover.data.api.services.AuthService
import com.licenta.postureimprover.data.api.services.AuthServiceImpl
import com.licenta.postureimprover.data.api.services.CaptureService
import com.licenta.postureimprover.data.api.services.CaptureServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    @Singleton
    abstract fun bindAuthService(authServiceImpl: AuthServiceImpl) : AuthService

    @Binds
    @Singleton
    abstract fun bindCaptureService(captureServiceImpl: CaptureServiceImpl) : CaptureService
}