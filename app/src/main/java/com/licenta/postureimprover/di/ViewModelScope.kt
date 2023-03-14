package com.licenta.postureimprover.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
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
}