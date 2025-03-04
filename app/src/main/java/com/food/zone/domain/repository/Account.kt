package com.food.zone.domain.repository

import com.food.zone.data.model.signin_data.SignUpData
import com.food.zone.data.model.token.Token
import com.food.zone.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Account {
    suspend fun signUpAccount(signUpData: SignUpData) : Flow<NetworkResult<Token>>
}