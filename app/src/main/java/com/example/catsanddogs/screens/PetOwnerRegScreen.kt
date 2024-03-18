package com.example.catsanddogs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.catsanddogs.data.SignupUIEvent
import com.example.catsanddogs.data.SignupViewModel

@Composable
fun PetOwnerRegScreen(onButtonClicked: () -> Unit,
                      signupViewModel: SignupViewModel = viewModel()
) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Surface()
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFDBE9))
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {

                TopSection()

                Spacer(modifier = Modifier.height(15.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                ) {
                    var fName = NormalTextField(
                        name = "First Name", modifier = Modifier.fillMaxWidth(),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                        },
                        errorStatus = signupViewModel.registrationUIState.value.firstNameError
                    )

                    var lName = NormalTextField(
                        name = "Last Name", modifier = Modifier.fillMaxWidth(),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                        },
                        errorStatus = signupViewModel.registrationUIState.value.lastNameError
                    )

                    var email = NormalTextField(
                        name = "Email", modifier = Modifier.fillMaxWidth(),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                        },
                        errorStatus = signupViewModel.registrationUIState.value.emailError
                    )

                    var phn = NormalTextField(
                        name = "Phone Number", modifier = Modifier.fillMaxWidth(),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.PhoneNumChanged(it))
                        },
                        errorStatus = signupViewModel.registrationUIState.value.phoneNumError
                    )

                    var sPass = PasswordTextField(
                        name = "Set Password", modifier = Modifier.fillMaxWidth(),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.SetPassChanged(it))
                        },
                        errorStatus = signupViewModel.registrationUIState.value.setPassError
                    )

//                    var cPass = PasswordTextField(
//                        name = "Confirm Password", modifier = Modifier.fillMaxWidth(),
//                        onTextSelected = {
//                            signupViewModel.onEvent(SignupUIEvent.ConfirmPassChanged(it))
//                        },
//                        errorStatus = signupViewModel.registrationUIState.value.confirmPassError
//                    )

                    Spacer(modifier = Modifier.height(20.dp))

                }
                buttonComponent(
                    onButtonClicked = onButtonClicked,
                    isEnabled = signupViewModel.allValidationPassedP.value
                )
            }
        }

        if (signupViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun buttonComponent(onButtonClicked: ()-> Unit,
                            isEnabled: Boolean){
    Button(
        modifier = Modifier
            .width(150.dp)
            .height(50.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        colors = ButtonDefaults.buttonColors(
            Color(218, 102, 147, 255),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 4.dp),
        enabled = isEnabled

    ){
        Text(
            text = "Register!",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
private fun TopSection(){


    Box (modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp)){
        Text(
            text = "Register as Pet Owner!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            lineHeight = 50.sp,
            textAlign = TextAlign.Center


        )
    }
}