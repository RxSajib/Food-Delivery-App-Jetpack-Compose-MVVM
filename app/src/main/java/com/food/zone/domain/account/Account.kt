package com.food.zone.domain.account

import com.food.zone.data.model.signin_data.SignUpData
import com.food.zone.data.model.token.Token
import com.food.zone.domain.repository.Account
import com.food.zone.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class Account constructor(val account: Account) {

    suspend operator fun invoke(signUpData: SignUpData) : Flow<NetworkResult<Token>> =
        account.signUpAccount(signUpData = signUpData)
}