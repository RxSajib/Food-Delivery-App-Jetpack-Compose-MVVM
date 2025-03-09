package com.food.zone.presentation

import com.food.zone.domain.model.restaurants.Data
import kotlinx.serialization.Serializable


sealed class AppRoute {
    @Serializable
    object SignInScreen

    @Serializable
    object SignUpScreen

    @Serializable
    object AccountScreen

    @Serializable
    object HomeScreen

    @Serializable
    data class CDetails(val name : String)

    @Serializable
    data class RestaurantDetails(val data : Data, val imageUrl : String)

    @Serializable
    object Cart

    @Serializable
    object Notification
}


