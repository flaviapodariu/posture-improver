package com.licenta.postureimprover.di

import com.licenta.postureimprover.data.api.services.*
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
    abstract fun bindAuthService(authServiceImpl: AuthApiImpl) : AuthApi

    @Binds
    @Singleton
    abstract fun bindCaptureApi(captureServiceImpl: CaptureApiImpl) : CaptureApi

    @Binds
    @Singleton
    abstract fun bindWorkoutService(workoutServiceImpl: WorkoutApiImpl) : WorkoutApi
}