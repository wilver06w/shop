package com.wfprogramin.shop.ui.splash.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wfprogramin.shop.ui.login.ui.Model
import com.wfprogramin.shop.ui.splash.ui.ModelSplash
import com.wfprogramin.shop.ui.splash.ui.SplashUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<SplashUIState>(SplashUIState.InitialState(ModelSplash()))
    val uiState: StateFlow<SplashUIState> = _uiState

    fun onLoginSuccess() {
        viewModelScope.launch {
            _uiState.value = SplashUIState.InitialState(_uiState.value.modelSplash)
            delay(4000)
            _uiState.value = SplashUIState.SuccessState(_uiState.value.modelSplash.copy(value = null))
        }
    }
}