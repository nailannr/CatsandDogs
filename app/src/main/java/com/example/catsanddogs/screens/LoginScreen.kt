package com.example.catsanddogs.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.catsanddogs.LoginViewModel
import com.example.catsanddogs.R
import com.example.catsanddogs.data.LoginUIEvent
import com.example.catsanddogs.data.SignupViewModel
import com.example.catsanddogs.data.SignupUIEvent
import kotlin.math.log

@Composable
fun LoginScreen(navController: NavController, onButtonClicked: () -> Unit,
                loginViewModel: LoginViewModel = viewModel()) {
    //val uiColor = if (isSystemInDarkTheme()) Color.White else Black
    Surface()
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFDBE9))) {

            TopSection()

            Spacer(modifier = Modifier.height(15.dp))

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .verticalScroll(
                    rememberScrollState()
                )){

                NormalTextField(name = "Email", modifier = Modifier.fillMaxWidth(),
                    onTextSelected = {
                                     loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                    )
                Spacer(modifier = Modifier.height(15.dp))
                PasswordTextField(name = "Password", modifier = Modifier.fillMaxWidth(),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError)
                Spacer(modifier = Modifier.height(25.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(
                        modifier = Modifier
                            .width(150.dp)
                            .height(50.dp),
                        onClick = {
                            navController.navigate(Routes.option_screen)
                        },
                        colors = ButtonDefaults.buttonColors(
                            Color(218, 102, 147, 255),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(size = 4.dp)

                    ) {
                        Text(
                            text = "New Here? Register instead",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                    
                    buttonComponent(onButtonClicked = onButtonClicked,
                        isEnabled = loginViewModel.allValidationsPassed.value)


                }
            }
        }
        if(loginViewModel.loginInProgress.value){
            CircularProgressIndicator()
        }
    }
}


@Composable
private fun buttonComponent(
    onButtonClicked: ()-> Unit,
    isEnabled: Boolean

){
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

    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}


@Composable
private fun TopSection(){

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(0.dp, 100.dp, 0.dp, 0.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = null
        )

    }
    Text(
        text = "Cats and Dogs",
        fontFamily = FontFamily.Serif,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 116.sp,
        textAlign = TextAlign.Center


    )
//    Text(
//        text = "Login to receive pet care at "
//    )
}