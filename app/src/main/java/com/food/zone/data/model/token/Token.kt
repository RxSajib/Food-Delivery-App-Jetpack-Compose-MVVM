package com.food.zone.data.model.token


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Token(
    @SerialName("token")
    val token: String
)