package com.food.zone.presentation.di

import com.food.zone.data.social.GoogleAuthProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocialLoginModule {

    @Provides
    @Singleton
    fun provideGoogleAuthProvider(): GoogleAuthProvider {
        return GoogleAuthProvider()
    }
}