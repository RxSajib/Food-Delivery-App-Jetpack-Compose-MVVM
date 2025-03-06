package com.food.zone.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.food.zone.data.model.category.CategoryResponse
import com.food.zone.data.model.use_case.CategoryUseCase
import com.food.zone.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(
    private val categoryUseCase: CategoryUseCase
) : ViewModel() {

    private val _categoryMutableStateFlow = MutableStateFlow<NetworkResult<CategoryResponse>>(NetworkResult.Empty())
    val categoryStateFlow = _categoryMutableStateFlow.asStateFlow()

    init {
        getCategory()
    }

    fun getCategory(){
        viewModelScope.launch {
            categoryUseCase.category.category.getAllCategory().collectLatest { result ->
                _categoryMutableStateFlow.value = result
            }
        }

    }
}