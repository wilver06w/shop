package com.wfprogramin.shop.ui.login.ui


sealed class LoginUIState {
    data object Loading : LoginUIState()
    data object Success : LoginUIState()
    data class Error(val msg: String) : LoginUIState()
}