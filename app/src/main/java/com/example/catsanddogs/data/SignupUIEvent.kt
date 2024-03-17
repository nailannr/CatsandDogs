package com.example.catsanddogs.data

import androidx.navigation.NavController

sealed class SignupUIEvent{
    data class FirstNameChanged(val firstName: String) : SignupUIEvent()
    data class LastNameChanged(val lastName: String) : SignupUIEvent()
    data class EmailChanged(val email: String) : SignupUIEvent()
    data class PhoneNumChanged(val phoneNum: String) : SignupUIEvent()
    data class LicenseChanged(val license: String) : SignupUIEvent()
    data class SetPassChanged(val setPass: String): SignupUIEvent()
    data class ConfirmPassChanged(val confirmPass: String): SignupUIEvent()

    data class RegisterButtonClicked(val navController: NavController) : SignupUIEvent()
}