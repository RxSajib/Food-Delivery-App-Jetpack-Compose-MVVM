package com.food.zone.data.local.api

import com.food.zone.data.model.dto.category.CategoryResponse
import com.food.zone.data.model.dto.restaurants.RestaurantsResponse
import com.food.zone.data.model.signin_data.SignUpData
import com.food.zone.data.model.token.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface API {

    @POST("auth/signup")
    suspend fun signUpAccount(@Body signUpData: SignUpData) : Response<Token>

    @GET("categories")
    suspend fun getCategory() : Response<CategoryResponse>

    @GET("restaurants")
    suspend fun getRestaurants(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double
    ) : Response<RestaurantsResponse>
}