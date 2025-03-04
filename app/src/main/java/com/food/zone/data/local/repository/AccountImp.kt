package com.food.zone.data.local.repository

import android.util.Log
import com.food.zone.data.local.api.API
import com.food.zone.data.model.signin_data.SignUpData
import com.food.zone.data.model.token.Token
import com.food.zone.domain.repository.Account
import com.food.zone.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import kotlin.math.log

private const val TAG = "AccountImp"
class AccountImp constructor(private val api: API) : Account{
    override suspend fun signUpAccount(signUpData: SignUpData): Flow<NetworkResult<Token>> {
        return flow {
            emit(NetworkResult.Loading())
            try {
                val response = api.signUpAccount(signUpData = signUpData)
                if(response.isSuccessful && response.body() != null){
                    emit(NetworkResult.Success(data = response.body()!!))
                    Log.d(TAG, "signUpAccount: success ${response.body()!!}")
                }else {
                    emit(NetworkResult.Error(message = "Something went wrong please try again"))
                    Log.d(TAG, "signUpAccount: error")
                }
            }catch (e : Exception){
                emit(NetworkResult.Error(message = "Something went wrong please try again"))
                Log.d(TAG, "signUpAccount: error 2 ${e.message}")
            }
        }
    }
}