package com.food.zone.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    private val _nameStateFlow = MutableStateFlow("")
    private val _emailStateFlow = MutableStateFlow("")
    private val _passwordStateFlow = MutableStateFlow("")

    val name = _nameStateFlow.asStateFlow()
    val email = _emailStateFlow.asStateFlow()
    val password = _passwordStateFlow.asStateFlow()

    fun saveName(name : String){
        _nameStateFlow.value = name
    }
    fun saveEmail(email : String){
        _emailStateFlow.value = email
    }
    fun savePassword(password : String){
        _passwordStateFlow.value = password
    }

}