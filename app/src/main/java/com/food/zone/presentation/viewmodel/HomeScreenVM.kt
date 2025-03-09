package com.food.zone.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.food.zone.data.model.dto.category.CategoryResponse
import com.food.zone.data.model.dto.restaurants.RestaurantsResponse
import com.food.zone.data.model.use_case.CategoryUseCase
import com.food.zone.data.model.use_case.RestaurantsUseCase
import com.food.zone.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeScreenVM"
@HiltViewModel
class HomeScreenVM @Inject constructor(
    private val categoryUseCase: CategoryUseCase,
    private val restaurantsUseCase: RestaurantsUseCase
) : ViewModel() {

    private val _categoryMutableStateFlow = MutableStateFlow<NetworkResult<CategoryResponse>>(NetworkResult.Empty())
    val categoryStateFlow = _categoryMutableStateFlow.asStateFlow()

    init {
        getCategory()
       /* getRestaurantsByCategory(
            lon = -74.0060,
            lat = 40.7128
        )*/
    }

    fun getCategory(){
        viewModelScope.launch {
            categoryUseCase.category.category.getAllCategory().collectLatest { result ->
                 _categoryMutableStateFlow.value = result
            }
        }
    }

    private val _restaurantsMutableStateFlow = MutableStateFlow<NetworkResult<RestaurantsResponse>>(NetworkResult.Empty())
    val restaurantsStateFlow = _restaurantsMutableStateFlow.asStateFlow()

    fun getRestaurantsByCategory(lat: Double, lon : Double){
        viewModelScope.launch {
            restaurantsUseCase.restaurants(lon = lon, lat = lat).collectLatest { result ->
                _restaurantsMutableStateFlow.value = result
            }
        }
    }
}