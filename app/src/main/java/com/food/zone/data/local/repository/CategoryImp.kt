package com.food.zone.data.local.repository

import com.food.zone.data.local.api.API
import com.food.zone.data.model.category.CategoryResponse
import com.food.zone.domain.repository.Category
import com.food.zone.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryImp constructor(
    private val api: API
) : Category {
    override suspend fun getAllCategory(): Flow<NetworkResult<CategoryResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            val response = api.getCategory()
            if(response.isSuccessful && response.body() != null){
                emit(NetworkResult.Success(data = response.body()!!))
            }else {
                emit(NetworkResult.Error(message = "Category not found"))
            }
        }
    }
}