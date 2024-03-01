package com.wfprogramin.shop.ui.login.ui.view


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.wfprogramin.shop.R
import com.wfprogramin.shop.ui.login.ui.LoginUIState
import com.wfprogramin.shop.ui.login.ui.viewmodel.LoginViewModel
import com.wfprogramin.shop.ui.splash.ui.SplashUIState
import com.wfprogramin.shop.ui.splash.ui.view.PrimaryButton
import com.wfprogramin.shop.ui.splash.ui.view.SecondaryOutLineButton
import com.wfprogramin.shop.ui.theme.Shapes
import com.wfprogramin.shop.ui.theme.caribbeanGreen
import com.wfprogramin.shop.ui.theme.crayolasGreen
import com.wfprogramin.shop.ui.theme.gunMetal
import com.wfprogramin.shop.ui.theme.lightSilver
import com.wfprogramin.shop.ui.theme.silverFoil
import com.wfprogramin.shop.util.BasicValues
import com.wfprogramin.shop.util.InputTypeInfo
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, quoteViewModel: LoginViewModel = hiltViewModel()) {

    val email: String by quoteViewModel.email.observeAsState(initial = "")
    val password: String by quoteViewModel.password.observeAsState(initial = "")
    val isLoading: Boolean by quoteViewModel.isLoading.observeAsState(initial = false)

    val loginEnabled: Boolean by quoteViewModel.loginEnabled.observeAsState(initial = false)

    val context = LocalContext.current
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        quoteViewModel.toastMessage.collect { message ->
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT,
            ).show()
        }

    }
    LaunchedEffect(Unit) {
        quoteViewModel.uiState.collect { uiState ->
            when (uiState) {
                is LoginUIState.Error -> {
                    Toast.makeText(
                        context,

                        uiState.msg,
                        Toast.LENGTH_SHORT,
                    ).show()
                }

                is LoginUIState.Success -> {
                    Toast.makeText(
                        context,
                        BasicValues.signSuccess,
                        Toast.LENGTH_SHORT,
                    ).show()
                }

                else -> {}
            }
        }

    }


    ProvideWindowInsets {
        Column(
            Modifier
                .navigationBarsWithImePadding()
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                BasicValues.enterYourEmailUsername,
                textAlign = TextAlign.Start,
                color = gunMetal,
                fontSize = 18.sp,
                modifier = Modifier
                    .offset(
                        x = 2.dp, y = 2.dp
                    )
                    .alpha(0.75f),
                fontWeight = FontWeight.Medium,
            )
            Spacer(
                modifier = Modifier.height(
                    30.dp
                )
            )
            TextInput(
                value = email,
                InputTypeInfo.Name,
                keyboardActions = KeyboardActions(onNext = {
                    passwordFocusRequester.requestFocus()
                }),
                onTextFieldChange = {
                    quoteViewModel.onLoginChanged(
                        email = it, password = password
                    )
                },
            )
            Spacer(
                modifier = Modifier.height(
                    16.dp
                )
            )
            TextInput(
                value = password,
                InputTypeInfo.Password,
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    coroutineScope.launch {
                        quoteViewModel.onLoginSuccess()
                    }
                }),
                focusRequester = passwordFocusRequester,
                onTextFieldChange = {
                    quoteViewModel.onLoginChanged(
                        email = email, password = it
                    )
                },
            )
            TextButton(
                onClick = {
                    coroutineScope.launch {
                        quoteViewModel.onLoginError()
                    }
                }, Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    BasicValues.didForgetPassword,
                    color = crayolasGreen,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                )
            }
            Spacer(
                modifier = Modifier.height(
                    24.dp
                )
            )
            if (isLoading) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            } else {
                PrimaryButton(
                    isEnabled = loginEnabled,
                    onClick = {
                        focusManager.clearFocus()
                        coroutineScope.launch {
                            quoteViewModel.onLoginSuccess()
                        }
                    },
                    text = BasicValues.next,
                )
            }
            Spacer(
                modifier = Modifier.height(
                    16.dp
                )
            )
            SecondaryOutLineButton(
                isEnabled = true,
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                text = BasicValues.createAccount,
            )
            Spacer(
                modifier = Modifier.height(
                    20.dp
                )
            )
            Text(
                BasicValues.loginWith,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 8.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(
                modifier = Modifier.height(
                    10.dp
                )
            )
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                ItemCircleSocial(imageSource = R.drawable.ic_google,
                    colorBorder = lightSilver,
                    onClick = {})
                Spacer(modifier = Modifier.width(10.dp))
                ItemCircleSocial(imageSource = R.drawable.ic_facebook,
                    colorBorder = lightSilver,
                    onClick = {})
                Spacer(modifier = Modifier.width(10.dp))
                ItemCircleSocial(imageSource = R.drawable.ic_apple,
                    colorBorder = lightSilver,
                    onClick = {})
                Spacer(modifier = Modifier.width(10.dp))
                ItemCircleSocial(imageSource = R.drawable.ic_microsoft,
                    colorBorder = lightSilver,
                    onClick = {})

            }
            Spacer(modifier = Modifier.height(24.dp))
            TextButton(
                onClick = {
                    coroutineScope.launch {
                        quoteViewModel.onLoginError()
                    }
                }, Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(BasicValues.needHelpEnter, color = crayolasGreen)
            }
        }
    }
}

@Composable
fun ItemCircleSocial(imageSource: Int, colorBorder: Color, onClick: () -> Unit) {
    Box(modifier = Modifier
        .clip(CircleShape)
        .border(1.dp, colorBorder, CircleShape)
        .size(40.dp)
        .padding(all = 2.dp)
        .clickable { }

    ) {
        Image(
            painter = painterResource(id = imageSource),
            "ic_google",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.matchParentSize(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
    value: String,
    inputType: InputTypeInfo,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions,
    onTextFieldChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester ?: FocusRequester()),
        leadingIcon = {
            Icon(
                imageVector = inputType.icon, null, tint = silverFoil
            )
        },

        placeholder = {
            Text(
                text = inputType.label, color = silverFoil
            )
        },
        shape = Shapes.small,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = caribbeanGreen,
            unfocusedBorderColor = silverFoil,
            focusedLeadingIconColor = caribbeanGreen,
        ),
        singleLine = true,
        keyboardOptions = inputType.keyboardOptions,
        visualTransformation = inputType.visualTransformation,
        keyboardActions = keyboardActions
    )
}