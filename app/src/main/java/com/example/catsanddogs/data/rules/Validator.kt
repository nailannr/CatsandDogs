package com.example.catsanddogs.data.rules

object Validator {
    fun valitadateFirstName(fName: String) : ValidationResult{
        return ValidationResult(
            (!fName.isNullOrEmpty())
        )
    }

    fun validateLastName(lName: String) : ValidationResult{
        return ValidationResult(
            (!lName.isNullOrEmpty())
        )
    }

    fun validateEmail(email: String) : ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty())
        )
    }

    fun validatePhoneNo(phoneNum: String) : ValidationResult{
        return ValidationResult(
            (!phoneNum.isNullOrEmpty() && phoneNum.length==11)
        )
    }

    fun validateLicense(license: String) : ValidationResult{
        return ValidationResult(
            (!license.isNullOrEmpty() && license.length == 4)
        )
    }

    fun validateDegree (degree: String) : ValidationResult{
        return ValidationResult(
            (!degree.isNullOrEmpty())
        )
    }

    fun validateWork (work: String) : ValidationResult{
        return ValidationResult(
            (!work.isNullOrEmpty())
        )
    }

    fun validateImage (image: String) : ValidationResult{
        return ValidationResult(
            (!image.isNullOrEmpty())
        )
    }

    fun validateSetPassword(setPass: String) : ValidationResult{
        return ValidationResult(
            (!setPass.isNullOrEmpty() && setPass.length>=6)
        )
    }

//    fun validateConfirmPassword(confirmPass: String) : ValidationResult{
//        return ValidationResult(
//            (!confirmPass.isNullOrEmpty() && confirmPass.length>=6)
//        )
//    }


}

data class ValidationResult(
    val status: Boolean = false

)