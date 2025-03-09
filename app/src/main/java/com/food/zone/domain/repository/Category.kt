package com.food.zone.domain.repository

import com.food.zone.data.model.dto.category.CategoryResponse
import com.food.zone.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Category {

    suspend fun getAllCategory() : Flow<NetworkResult<CategoryResponse>>
}