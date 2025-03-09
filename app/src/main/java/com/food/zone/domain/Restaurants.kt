package com.food.zone.domain

import com.food.zone.data.model.dto.restaurants.RestaurantsResponse
import com.food.zone.domain.repository.Restaurants
import com.food.zone.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class Restaurants constructor(
    private val restaurants: Restaurants
) {

    suspend operator fun invoke(lat : Double, lon : Double) : Flow<NetworkResult<RestaurantsResponse>> = restaurants.getRestaurants(
        lat = lat,
        lon = lon
    )
}