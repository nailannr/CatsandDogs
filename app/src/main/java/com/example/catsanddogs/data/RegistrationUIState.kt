package com.example.catsanddogs.data

data class RegistrationUIState(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var phoneNum: String =  "",
    var license: String= "",
    var setPass: String = "",
    var confirmPass: String = "",

    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
    var emailError: Boolean = false,
    var phoneNumError: Boolean = false,
    var licenseError: Boolean = false,
    var setPassError: Boolean = false,
    var confirmPassError: Boolean = false
)
