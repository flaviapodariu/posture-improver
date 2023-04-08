package com.licenta.postureimprover.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.licenta.postureimprover.data.api.services.CaptureApi
import com.licenta.postureimprover.data.local.PostureDatabase
import com.licenta.postureimprover.data.repositories.CaptureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)

object ViewModelScope {
    @Provides
    @ViewModelScoped
    fun providePreferences(app : Application) : SharedPreferences {
        return app.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @ViewModelScoped
    fun provideCaptureRepository(api: CaptureApi, db: PostureDatabase) : CaptureRepository {
        return CaptureRepository(api, db)
    }

//    @Provides
//    @ViewModelScoped
//    fun provideWorkoutRepository(api: WorkoutApi, db:PostureDatabase) : WorkoutRepository {
//        return WorkoutRepository(api, db)
//    }
}