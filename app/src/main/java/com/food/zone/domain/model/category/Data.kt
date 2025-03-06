package com.food.zone.domain.model.category


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Data(
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("id")
    val id: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("name")
    val name: String
)