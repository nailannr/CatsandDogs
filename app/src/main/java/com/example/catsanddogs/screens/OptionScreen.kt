package com.example.catsanddogs.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.catsanddogs.R
import com.example.catsanddogs.data.RegistrationUIState
import com.example.catsanddogs.data.SignupUIEvent
import com.example.catsanddogs.data.SignupViewModel

@Composable
fun OptionScreen(navController: NavController,
                 signupViewModel: SignupViewModel = viewModel()){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color(255, 219, 233)),
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(0.dp, 120.dp, 0.dp, 0.dp)
                .size(160.dp)

        ){
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "logo"

            )
        }
        Text(
            text= "Cats and Dogs",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )

        Text(
            text="Register as a",
            color = Color.Black,
            modifier = Modifier.padding(48.dp,80.dp,50.dp,10.dp),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Button(onClick = {
            signupViewModel.onEvent(SignupUIEvent.FromVetButton)
            navController.navigate(Routes.vet_registration)
        },
            modifier = Modifier
                .height(70.dp)
                .width(210.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 20.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(218, 102, 147, 255)
            ),
            shape = RoundedCornerShape(25.dp)
        ){
            Text(
                text="Veterinarian",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold

            )
        }

        Button(onClick = {
            signupViewModel.onEvent(SignupUIEvent.FromOwnerButton)
            navController.navigate(Routes.pet_owner_reg)
        },
            modifier = Modifier
                .height(70.dp)
                .width(210.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 20.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(218, 102, 147, 255)
            ),
            shape = RoundedCornerShape(25.dp)
        ){
            Text(
                text="Pet Owner",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold

            )
        }

    }

}