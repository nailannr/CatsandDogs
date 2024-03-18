package com.example.catsanddogs

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.catsanddogs.data.LoginUIEvent
import com.example.catsanddogs.data.LoginUIState
import com.example.catsanddogs.data.rules.Validator
import com.example.catsanddogs.screens.Routes
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel(){
    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event:LoginUIEvent){
        validateLoginUIDataWithRules()
        when(event){
            is LoginUIEvent.EmailChanged ->{
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }
            is LoginUIEvent.PasswordChanged ->{
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked ->{
                login(navController = event.navController)
            }

            is LoginUIEvent.LogoutButtonClicked ->{
                logout()
                loginUIState.value = loginUIState.value.copy(
                    email = ""
                )
                loginUIState.value = loginUIState.value.copy(
                    password = ""
                )
            }
        }

        validateLoginUIDataWithRules()
    }


    private fun validateLoginUIDataWithRules(){
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )

        val passwordResult = Validator.validateSetPassword(
            setPass = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }

    private fun login(navController: NavController) {
        loginInProgress.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                loginInProgress.value = false
                if(it.isSuccessful){
                    navController.navigate(Routes.home_screen)
                }
            }
            .addOnFailureListener{

            }
    }
    private fun logout() {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
    }

}