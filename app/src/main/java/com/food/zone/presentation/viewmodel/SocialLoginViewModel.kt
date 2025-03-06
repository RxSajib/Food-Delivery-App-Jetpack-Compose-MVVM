package com.food.zone.presentation.viewmodel

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.food.zone.data.model.google.GoogleAccount
import com.food.zone.data.social.GoogleAuthProvider
import com.food.zone.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


/*
@HiltViewModel
class SocialLoginViewModel @Inject constructor(
     val credentialManager: GoogleAuthProvider
) : ViewModel() {



    private var _isLoginWithGoogleStateFlow = MutableStateFlow<NetworkResult<GoogleAccount>>(NetworkResult.Empty())
    val googleSateFlow = _isLoginWithGoogleStateFlow.asStateFlow()

    fun loginWithGoogle(context: Context){
        viewModelScope.launch {
            _isLoginWithGoogleStateFlow.value = NetworkResult.Loading()
         val accountInfo = credentialManager.signIn(context = context, credentialManager = CredentialManager.create(context))

            if(accountInfo != null){
                _isLoginWithGoogleStateFlow.value = NetworkResult.Success(data = accountInfo)
            }else{
                _isLoginWithGoogleStateFlow.value = NetworkResult.Error(message = "Something went wrong")
            }
        }
    }
}
*/
