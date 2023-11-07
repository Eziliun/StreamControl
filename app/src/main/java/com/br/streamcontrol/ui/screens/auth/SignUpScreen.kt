package com.br.streamcontrol.ui.screens.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.streamcontrol.R
import com.br.streamcontrol.data.auth.signup.SignupUIEvent
import com.br.streamcontrol.data.auth.signup.SignUpViewModel
import com.br.streamcontrol.domain.routes.Router
import com.br.streamcontrol.domain.routes.Screen
import com.br.streamcontrol.domain.routes.SystemBackButtonHandler
import com.br.streamcontrol.ui.widgets.ButtonComponent
import com.br.streamcontrol.ui.widgets.CheckboxComponent
import com.br.streamcontrol.ui.widgets.ClickableLoginTextComponent
import com.br.streamcontrol.ui.widgets.DividerTextComponent
import com.br.streamcontrol.ui.widgets.HeadingTextComponent
import com.br.streamcontrol.ui.widgets.MyTextFieldComponent
import com.br.streamcontrol.ui.widgets.NormalTextComponent
import com.br.streamcontrol.ui.widgets.PasswordTextFieldComponent

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = viewModel()) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        ) {

            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.first_name),
                painterResource(id = R.drawable.profile),
                fieldName = "Primeiro nome",
                onTextChanged = {
                    signUpViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.firstNameError
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.profile),
                fieldName = "Sobrenome",
                onTextChanged = {
                    signUpViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.lastNameError
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.message),
                onTextChanged = {
                    signUpViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                },
                fieldName = "E-mail",
                errorStatus = signUpViewModel.registrationUIState.value.emailError
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.ic_lock),
                onTextSelected = {
                    signUpViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.passwordError
            )

            CheckboxComponent(
                value = stringResource(id = R.string.terms_and_conditions),
                onTextSelected = {
//                        Router.navigateTo(Screen.TermsAndConditionsScreen)
                },
                onCheckedChange = {
                    signUpViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            ButtonComponent(
                value = stringResource(id = R.string.register),
                onButtonClicked = {
                    signUpViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                },
                isEnabled = signUpViewModel.allValidationsPassed.value
            )

            Spacer(modifier = Modifier.height(15.dp))

            DividerTextComponent()

            Spacer(modifier = Modifier.height(15.dp))

            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                Router.navigateTo(Screen.LoginScreen)
            })
        }
        if (signUpViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }
    }
    SystemBackButtonHandler {
        Router.navigateTo(Screen.LoginScreen)
    }
}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}