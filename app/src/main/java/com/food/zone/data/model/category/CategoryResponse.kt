package com.food.zone.data.model.category


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep
import com.food.zone.domain.model.category.Data

@Keep
@Serializable
data class CategoryResponse(
    @SerialName("data")
    val `data`: List<Data>
)