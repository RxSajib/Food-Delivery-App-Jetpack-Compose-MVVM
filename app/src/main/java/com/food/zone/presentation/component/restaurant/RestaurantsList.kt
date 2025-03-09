package com.food.zone.presentation.component.restaurant

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.food.zone.domain.model.restaurants.Data

@Composable
fun RestaurantsItem(list : List<Data>, onClick: (Data) -> Unit) {
    LazyRow{
        itemsIndexed(list){index, item ->
            RestaurantItem(
                data = item,
                onClick = {
                    onClick.invoke(it)
                }
            )
        }
    }
}