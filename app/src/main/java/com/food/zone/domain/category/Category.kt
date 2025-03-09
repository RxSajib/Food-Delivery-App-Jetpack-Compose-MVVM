package com.food.zone.domain.category

import com.food.zone.data.model.dto.category.CategoryResponse
import com.food.zone.domain.repository.Category
import com.food.zone.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class Category constructor(val category: Category) {

    suspend operator fun invoke() : Flow<NetworkResult<CategoryResponse>> {
        return category.getAllCategory()
    }
}