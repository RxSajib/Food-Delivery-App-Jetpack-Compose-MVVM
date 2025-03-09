package com.food.zone.data.local.repository

import android.util.Log
import com.food.zone.data.local.api.API
import com.food.zone.data.model.dto.restaurants.RestaurantsResponse
import com.food.zone.domain.repository.Restaurants
import com.food.zone.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val TAG = "RestaurantsImp"
class RestaurantsImp constructor(
    private val api: API
) : Restaurants {
    override suspend fun getRestaurants(
        lat: Double,
        lon: Double
    ): Flow<NetworkResult<RestaurantsResponse>> {
        return flow {
            try {
                emit(NetworkResult.Loading())
                val response = api.getRestaurants(lat = lat, lon = lon)
                if(response.isSuccessful && response.body() != null){
                    Log.d(TAG, "getRestaurants: success")
                    emit(NetworkResult.Success(data = response.body()!!))
                }else {
                    Log.d(TAG, "getRestaurants: error1")
                    emit(NetworkResult.Error(message = "Something went wrong"))
                }
            }catch (e : Exception){
                Log.d(TAG, "getRestaurants: error 2")
                emit(NetworkResult.Error(message = "something went wrong ${e.message}"))
            }
        }
    }
}