package com.food.zone.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.food.zone.data.model.signin_data.SignUpData
import com.food.zone.data.model.token.Token
import com.food.zone.data.model.use_case.AccountUseCase
import com.food.zone.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountUseCase: AccountUseCase
) : ViewModel() {

    private val _nameStateFlow = MutableStateFlow("")
    private val _emailStateFlow = MutableStateFlow("")
    private val _passwordStateFlow = MutableStateFlow("")

    val name = _nameStateFlow.asStateFlow()
    val email = _emailStateFlow.asStateFlow()
    val password = _passwordStateFlow.asStateFlow()

    fun saveName(name: String) {
        _nameStateFlow.value = name
    }

    fun saveEmail(email: String) {
        _emailStateFlow.value = email
    }

    fun savePassword(password: String) {
        _passwordStateFlow.value = password
    }


    private val _signinStateFlow = MutableStateFlow<NetworkResult<Token>>(NetworkResult.Empty())
    val signinStateFlow: StateFlow<NetworkResult<Token>> = _signinStateFlow.asStateFlow()

    fun signUpAccount(signUpData: SignUpData) {
        viewModelScope.launch {
            accountUseCase.accountUseCase.account.signUpAccount(signUpData = signUpData)
                .collectLatest { result ->
                    _signinStateFlow.value = result
                }
        }
    }
}