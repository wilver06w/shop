package com.wfprogramin.shop.ui.splash.ui.view


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.wfprogramin.shop.R
import com.wfprogramin.shop.navigation.AppScreens
import com.wfprogramin.shop.ui.splash.ui.SplashUIState
import com.wfprogramin.shop.ui.splash.ui.viewmodel.SplashViewModel
import com.wfprogramin.shop.ui.theme.caribbeanGreen
import com.wfprogramin.shop.ui.theme.crayolasGreen
import com.wfprogramin.shop.ui.theme.gunMetal
import com.wfprogramin.shop.util.BasicValues
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController ,splashViewModel: SplashViewModel = hiltViewModel()) {

    splashViewModel.onLoginSuccess()

    val context = LocalContext.current
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        splashViewModel.uiState.collect { uiState ->
            when (uiState) {
                is SplashUIState.SuccessState -> {
                    Toast.makeText(
                        context,
                        "${BasicValues.signSuccess} ${uiState.model.itemYugiOh}",
                        Toast.LENGTH_SHORT,
                    ).show()
                    navController.navigate(route = AppScreens.LoginScreen.route)
                }

                else -> {}
            }
        }

    }


    ProvideWindowInsets {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.background_splash),
                "Background",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.height(330.dp)
            )
        }
        Column(
            Modifier
                .navigationBarsWithImePadding()
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = R.drawable.hello_splash),
                "hello_splash",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(81.dp)
                    .width(118.dp),
            )
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.hi_splash),
                "hi_splash",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(194.dp)
                    .width(195.dp),
            )
            Spacer(
                modifier = Modifier.height(
                    10.dp
                )
            )
            Text(
                BasicValues.weLoveHavingShop,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = gunMetal
            )
            Spacer(
                modifier = Modifier.height(
                    10.dp
                )
            )
            Text(
                BasicValues.wePreparingEverythingPurchase,
                color = gunMetal,
                fontSize = 10.sp

            )
            Image(
                painter = painterResource(id = R.drawable.arrow),
                "arrow_splash",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp),
            )
        }
    }
}

@Composable
fun PrimaryButton(
    isEnabled: Boolean, onClick: () -> Unit,
    text: String,
) {
    Button(onClick = onClick, modifier = Modifier.fillMaxWidth(), enabled = isEnabled) {
        Text(text, Modifier.padding(vertical = 8.dp))
    }
}


@Composable
fun SecondaryOutLineButton(
    isEnabled: Boolean, onClick: () -> Unit,
    modifier: Modifier,
    text: String,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        border = BorderStroke(1.dp, caribbeanGreen),
        shape = RoundedCornerShape(50), // = 50% percent
        // or shape = CircleShape
        colors = ButtonDefaults.outlinedButtonColors(contentColor = caribbeanGreen),
        enabled = isEnabled,
    ) {
        Text(
            text = text,
            color = crayolasGreen,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
    }
//    Button(onClick = onClick, modifier = Modifier.fillMaxWidth(), enabled = isEnabled) {
//        Text(BasicValues.signIn, Modifier.padding(vertical = 8.dp))
//    }
}
