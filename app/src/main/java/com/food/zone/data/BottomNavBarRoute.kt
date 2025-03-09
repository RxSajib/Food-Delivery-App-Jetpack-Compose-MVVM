package com.food.zone.data

import com.food.zone.R
import com.food.zone.presentation.AppRoute


sealed class BottomNavBarRoute(val route : Any, val title : String, val icon : Int) {

    object Home : BottomNavBarRoute(route = AppRoute.HomeScreen, title = "Home", icon = R.drawable.home_svgrepo_com)

    object MyCart : BottomNavBarRoute(route = AppRoute.Cart, title = "Cart", icon = R.drawable.cart_shopping_svgrepo_com)

    object MyNotification : BottomNavBarRoute(route = AppRoute.Notification, title = "Notification", icon = R.drawable.notification_svgrepo_com)
}