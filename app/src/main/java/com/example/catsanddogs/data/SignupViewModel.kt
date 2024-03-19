package com.example.catsanddogs.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.catsanddogs.data.rules.Validator
import com.example.catsanddogs.screens.Routes
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class SignupViewModel : ViewModel() {
    var registrationUIState = mutableStateOf(RegistrationUIState())

    var loginUIState = mutableStateOf(LoginUIState())

    val allValidationPassedP = mutableStateOf(false)
    val allValidationPassedV = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignupUIEvent){
        validateDataWithRules()
        when(event){
            is SignupUIEvent.FirstNameChanged ->{
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
            }
            is SignupUIEvent.LastNameChanged ->{
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
            }
            is SignupUIEvent.EmailChanged ->{
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }
            is SignupUIEvent.PhoneNumChanged ->{
                registrationUIState.value = registrationUIState.value.copy(
                    phoneNum = event.phoneNum
                )
            }
            is SignupUIEvent.LicenseChanged ->{
                registrationUIState.value = registrationUIState.value.copy(
                    license = event.license
                )
            }
            is SignupUIEvent.SetPassChanged ->{
                registrationUIState.value = registrationUIState.value.copy(
                    setPass = event.setPass
                )
            }
//            is SignupUIEvent.ConfirmPassChanged ->{
//                registrationUIState.value = registrationUIState.value.copy(
//                    confirmPass = event.confirmPass
//                )
//            }

            is SignupUIEvent.DegreeChanged ->{
                registrationUIState.value = registrationUIState.value.copy(
                    degree = event.degree
                )
            }

            is SignupUIEvent.WorkChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    work = event.work
                )
            }

            is SignupUIEvent.ImageChanged ->{
                registrationUIState.value = registrationUIState.value.copy(
                    image = event.image
                )
            }

            is SignupUIEvent.RegisterButtonClicked ->{
                val db = Firebase.firestore
                val newProfile = hashMapOf(
                    "firstName" to registrationUIState.value.firstName,
                    "lastName" to registrationUIState.value.lastName,
                    "email" to registrationUIState.value.email,
                    "contactNo" to registrationUIState.value.phoneNum,
                    "role" to if(registrationUIState.value.asVet) "Veterinarian" else "Pet Owner"
                )
                db.collection("profile")
                    .add(newProfile)
                    .addOnSuccessListener { documentReference ->
                        //Log.d("newHouseAdded", "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        // Log.w("failedHouseAdded", "Error adding document", e)
                    }
                signUp(navController = event.navController)
            }

            is SignupUIEvent.FromOwnerButton -> {
                registrationUIState.value = registrationUIState.value.copy(
                    asVet = false
                )
            }

            is SignupUIEvent.FromVetButton -> {
                registrationUIState.value = registrationUIState.value.copy(
                    asVet = false
                )
            }

//            is SignupUIEvent.VetRegisterButtonClicked ->{
//                signUp(navController = event.navController)
//            }
        }
    }

    private fun signUp(navController: NavController){
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.setPass,
            navController = navController
        )
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.valitadateFirstName(
            fName = registrationUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )

        val phoneNumResult = Validator.validatePhoneNo(
            phoneNum = registrationUIState.value.phoneNum
        )

        val licenseResult = Validator.validateLicense(
            license = registrationUIState.value.license
        )

        val degreeResult = Validator.validateDegree(
            degree = registrationUIState.value.degree
        )

        val workResult = Validator.validateWork(
            work = registrationUIState.value.work
        )

        val imageResult = Validator.validateImage(
            image = registrationUIState.value.image
        )

        val setPassResult = Validator.validateSetPassword(
            setPass = registrationUIState.value.setPass
        )

//        val confirmPassResult = Validator.validateConfirmPassword(
//            confirmPass = registrationUIState.value.confirmPass
//        )

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            phoneNumError = phoneNumResult.status,
            licenseError = licenseResult.status,
            setPassError = setPassResult.status,
//            confirmPassError = confirmPassResult.status,
            degreeError = degreeResult.status,
            workError = workResult.status,
            imageError = imageResult.status
        )

        allValidationPassedV.value = fNameResult.status &&
                lNameResult.status &&
                emailResult.status &&
                phoneNumResult.status &&
//                licenseResult.status &&
                degreeResult.status &&
                workResult.status &&
//                imageResult.status &&
                setPassResult.status
//                confirmPassResult.status

        allValidationPassedP.value = fNameResult.status &&
                lNameResult.status &&
                emailResult.status &&
                phoneNumResult.status &&
                setPassResult.status
//                confirmPassResult.status

    }

    private fun createUserInFirebase(email: String, password: String,
                                     navController: NavController){
        signUpInProgress.value = true
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                signUpInProgress.value = false

                if(it.isSuccessful){
                    navController.navigate(Routes.home_screen)
                }
            }
            .addOnFailureListener{

            }
    }
}