package com.food.zone.presentation.di

import com.food.zone.data.local.api.API
import com.food.zone.data.datamanager.DataManager
import com.food.zone.data.local.repository.AccountImp
import com.food.zone.domain.repository.Account
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(DataManager.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) : API = retrofit.create(API::class.java)

    @Singleton
    @Provides
    fun provideRepositoryImp(api: API) : Account = AccountImp(api = api)

    @Singleton
    @Provides
    fun provideAccountUserCase(account: Account) : com.food.zone.data.model.use_case.AccountUseCase =
        com.food.zone.data.model.use_case.AccountUseCase(accountUseCase = com.food.zone.domain.account.Account(account = account))
}