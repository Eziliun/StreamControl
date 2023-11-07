package com.br.streamcontrol.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.streamcontrol.R
import com.br.streamcontrol.data.auth.login.AuthViewModel
import com.br.streamcontrol.data.auth.login.LoginUIEvent
import com.br.streamcontrol.domain.routes.Router
import com.br.streamcontrol.domain.routes.Screen
import com.br.streamcontrol.ui.screens.dialogs.LoadingBottomSheet
import com.br.streamcontrol.ui.widgets.ButtonComponent
import com.br.streamcontrol.ui.widgets.ClickableLoginTextComponent
import com.br.streamcontrol.ui.widgets.DividerTextComponent
import com.br.streamcontrol.ui.widgets.HeadingTextComponent
import com.br.streamcontrol.ui.widgets.MyTextFieldComponent
import com.br.streamcontrol.ui.widgets.PasswordTextFieldComponent

@Composable
fun LoginScreen(loginViewModel: AuthViewModel = viewModel()) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BackgroundImage {
            Image(
                painter = painterResource(id = R.drawable.logo),
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentDescription = ""
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            HeadingTextComponent(value = stringResource(id = R.string.login))

            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.message),
                onTextChanged = {
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                },
                fieldName = "E-mail",
                errorStatus = loginViewModel.loginUIState.value.emailError,
            )


            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.lock),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                },
                errorStatus = loginViewModel.loginUIState.value.passwordError,
            )

            Spacer(modifier = Modifier.height(20.dp))

            ButtonComponent(
                value = stringResource(id = R.string.login),
                onButtonClicked = {
                    loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                },
                isEnabled = loginViewModel.allValidationsPassed.value
            )

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            Spacer(modifier = Modifier.height(20.dp))

            ClickableLoginTextComponent(
                tryingToLogin = false,
                onTextSelected = {
                    Router.navigateTo(Screen.SignUpScreen)
                }
            )
        }
        if (loginViewModel.loginError.value || loginViewModel.loginInProgress.value) {
            LoadingBottomSheet(
                errorMessage = loginViewModel.errorMessage.value,
                onDismiss = {
                    loginViewModel.onEvent(LoginUIEvent.DismissError)
                }
            )
        }
    }
}

@Composable
fun BackgroundImage(
    content: @Composable () -> Unit
) {
    val backgroundImage = painterResource(id = R.drawable.banner)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth().height(170.dp)
        )
        content()
    }
}
