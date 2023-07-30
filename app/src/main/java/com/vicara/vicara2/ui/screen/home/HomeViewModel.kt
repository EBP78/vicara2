package com.vicara.vicara2.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vicara.vicara2.data.Repository

class HomeViewModel (private val repository: Repository) : ViewModel() {

    private val _isLogin = mutableStateOf(false)
    val isLogin : State<Boolean> get() = _isLogin

    private val _show = mutableStateOf(false)
    val show : State<Boolean> get() = _show

    fun showPopup(){
        _show.value = true
    }

    fun closePopup(){
        _show.value = false
    }

    init {
        checkLogin()
    }

    private fun checkLogin() {
        _isLogin.value = repository.isLogin()
    }

    fun login(){
        repository.login()
        checkLogin()
    }

    fun logout(){
        repository.logout()
        checkLogin()
    }
}