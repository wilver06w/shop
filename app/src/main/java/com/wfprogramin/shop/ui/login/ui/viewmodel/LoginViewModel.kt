package com.wfprogramin.shop.ui.login.ui.viewmodel


import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wfprogramin.shop.ui.login.ui.LoginUIState
import com.wfprogramin.shop.ui.login.ui.Model
import com.wfprogramin.shop.ui.splash.ui.ModelSplash
import com.wfprogramin.shop.ui.splash.ui.SplashUIState
import com.wfprogramin.shop.util.BasicValues
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

//    private val _email = MutableLiveData<String>()
//    val email: LiveData<String> = _email
//    private val _passWord = MutableLiveData<String>()
//    val password: LiveData<String> = _passWord
//    private val _loginEnabled = MutableLiveData<Boolean>()
//    val loginEnabled: LiveData<Boolean> = _loginEnabled
//
//    val isLoading = MutableLiveData<Boolean>()

//    private val _toastMessage = MutableSharedFlow<String>()
//    val toastMessage = _toastMessage.asSharedFlow()


    private val _uiState = MutableStateFlow<LoginUIState>(LoginUIState.InitialState(Model()))
    val uiState: StateFlow<LoginUIState> = _uiState

//    private val _uiState = MutableStateFlow<LoginUIState>(LoginUIState.InitialState(Model()))
//    val uiState: StateFlow<LoginUIState> = _uiState

//    fun sendMessage(message: String) {
//        viewModelScope.launch {
//            _toastMessage.emit(message)
//        }
//    }

    fun onLoginChanged(email: String, password: String) {
        _uiState.value = LoginUIState.ChangedData(_uiState.value.model.copy(email = email))
        _uiState.value = LoginUIState.ChangedData(_uiState.value.model.copy(password = password))
        _uiState.value = LoginUIState.ChangedData(_uiState.value.model.copy(loginEnabled = isValidEmail(email) && isValidPassword(password)))
//        _email.value = email
//        _passWord.value = password
//        _loginEnabled.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length > 6

    suspend fun onLoginSuccess() {
        _uiState.value = LoginUIState.LoadingState(_uiState.value.model)
        delay(4000)
        _uiState.value = LoginUIState.SuccessState(_uiState.value.model)
    }

    suspend fun onLoginError() {
        _uiState.value = LoginUIState.LoadingState(_uiState.value.model)
        delay(4000)
        _uiState.value = LoginUIState.ErrorState(_uiState.value.model)
    }
}