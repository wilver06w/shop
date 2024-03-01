package com.wfprogramin.shop.ui.splash.ui.viewmodel


import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wfprogramin.shop.ui.splash.ui.Model
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
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<SplashUIState>(SplashUIState.InitialState(Model()))
    val uiState: StateFlow<SplashUIState> = _uiState

    fun onLoginSuccess() {
        viewModelScope.launch {
            _uiState.value = SplashUIState.InitialState(_uiState.value.model)
            delay(4000)
            _uiState.value = SplashUIState.SuccessState(_uiState.value.model.copy(itemYugiOh = 2))
        }
    }
}