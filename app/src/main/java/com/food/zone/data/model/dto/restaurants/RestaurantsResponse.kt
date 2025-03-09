package com.food.zone.data.model.dto.restaurants


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep
import com.food.zone.domain.model.restaurants.Data

@Keep
@Serializable
data class RestaurantsResponse(
    @SerialName("data")
    val `data`: List<Data>
)