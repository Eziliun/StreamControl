package com.br.streamcontrol.data.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.br.streamcontrol.domain.rules.ValidationResult
import com.br.streamcontrol.domain.rules.Validator
import com.br.streamcontrol.domain.rules.handleFirebaseAuthErrorMessage
import com.br.streamcontrol.domain.routes.Router
import com.br.streamcontrol.domain.routes.Screen
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val TAG = AuthViewModel::class.simpleName

    var loginError = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)
    private var isEmailFieldTouched = mutableStateOf(false)
    private var isPasswordFieldTouched = mutableStateOf(false)
    var allValidationsPassed = mutableStateOf(false)
    var loginInProgress = mutableStateOf(false)
    var loginUIState = mutableStateOf(
        LoginUIState(
            emailError = false,
            passwordError = false
        )
    )

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
                isEmailFieldTouched.value = true
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
                isPasswordFieldTouched.value = true
            }
            is LoginUIEvent.DismissError -> {
                loginError.value = false
                errorMessage.value = null
            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = if (isEmailFieldTouched.value && loginUIState.value.email.isNotEmpty()) {
            Validator.validateEmail(loginUIState.value.email)
        } else {
            ValidationResult(status = false)
        }

        val passwordResult =
            if (isPasswordFieldTouched.value && loginUIState.value.password.isNotEmpty()) {
                Validator.validatePassword(loginUIState.value.password)
            } else {
                ValidationResult(status = false)
            }

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status == true && passwordResult.status == true
    }


    private fun login() {
        loginInProgress.value = true
        val email = loginUIState.value.email.trim()
        val password = loginUIState.value.password.trim()

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    loginInProgress.value = false
                    Router.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener { exception ->
                loginInProgress.value = false
                println(exception)
                val processedErrorMessage = handleFirebaseAuthErrorMessage(exception.localizedMessage ?: "")
                errorMessage.value = processedErrorMessage
                loginError.value = true
            }
    }
}
