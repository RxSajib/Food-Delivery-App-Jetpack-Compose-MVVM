package com.food.zone.data.local.api

import com.food.zone.data.model.category.CategoryResponse
import com.food.zone.data.model.signin_data.SignUpData
import com.food.zone.data.model.token.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {

    @POST("auth/signup")
    suspend fun signUpAccount(@Body signUpData: SignUpData) : Response<Token>

    @GET("categories")
    suspend fun getCategory() : Response<CategoryResponse>
}