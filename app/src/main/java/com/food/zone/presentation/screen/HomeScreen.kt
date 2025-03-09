package com.food.zone.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.food.zone.domain.model.category.Data
import com.food.zone.presentation.component.category.CategoryList
import com.food.zone.presentation.component.restaurant.RestaurantItem
import com.food.zone.presentation.component.restaurant.RestaurantsItem
import com.food.zone.presentation.viewmodel.HomeScreenVM
import com.food.zone.utils.NetworkResult

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    gotoRestaurantDetails: (com.food.zone.domain.model.restaurants.Data) -> Unit,
    gotoHome: (Data) -> Unit,
    viewmodel: HomeScreenVM = hiltViewModel()
) {

    val category = viewmodel.categoryStateFlow.collectAsState()
    val restaurantsList = viewmodel.restaurantsStateFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Text(
            text = "Home Screen"
        )

       when (category.value) {
            is NetworkResult.Empty -> {
                Log.d(TAG, "HomeScreen: empty")
            }

            is NetworkResult.Success -> {
                CategoryList(
                    category = category.value.data?.data ?: emptyList(),
                    click = {
                        gotoHome.invoke(it)
                    }
                )
            }

            is NetworkResult.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is NetworkResult.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category.value.message.toString()
                    )
                }
            }
        }



        Spacer(modifier = Modifier.height(10.dp))





        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "All Restaurants"
            )

            TextButton(onClick = {}) {
                Text(
                    text = "Show All"
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))

        when(restaurantsList.value){
            is NetworkResult.Error -> {
                Log.d(TAG, "HomeScreen: error")
            }
            is NetworkResult.Success -> {
                Log.d(TAG, "HomeScreen: success")
                RestaurantsItem(
                    list = restaurantsList.value.data?.data ?: emptyList(),
                    onClick = {
                        gotoRestaurantDetails.invoke(it)
                    }
                )
            }
            is NetworkResult.Loading -> {
                Log.d(TAG, "HomeScreen: loading")
            }
            is NetworkResult.Empty -> {
                
            }
        }

    }
}