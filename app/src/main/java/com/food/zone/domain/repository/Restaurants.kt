package com.food.zone.domain.repository

import com.food.zone.data.model.dto.restaurants.RestaurantsResponse
import com.food.zone.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Restaurants {

    suspend fun getRestaurants(lat : Double, lon : Double) : Flow<NetworkResult<RestaurantsResponse>>
}