package com.example.catsanddogs.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catsanddogs.LoginViewModel
import com.example.catsanddogs.R
import com.example.catsanddogs.data.LoginUIEvent
import com.example.catsanddogs.data.SignupViewModel
import com.example.catsanddogs.data.SignupUIEvent

@Preview(showSystemUi = true)
@Composable
fun AppNavigationGraph(signupViewModel: SignupViewModel = viewModel(),
                       loginViewModel: LoginViewModel = viewModel()) {

    val imageId = arrayOf(
        R.drawable.male_doc,
        R.drawable.femaledoc,
        R.drawable.male_doc,
        R.drawable.femaledoc,
        R.drawable.male_doc
    )
    val names = arrayOf(
        "Dr. Md. Mosharaf Hossain",
        "Dr. Ruksana Ahmed",
        "Dr. Abdullah-Al-Aziz",
        "Dr. Muslima Rahman",
        "Dr. Tasmir Rayan"
    )
    val degrees = arrayOf(
        "DVM, Phd (JAPAN) MS",
        "DVM, MS",
        "DVM",
        "DVM",
        "DVM"
    )
    val work = arrayOf(
        "Sher-e-Bangla Agricultural University",
        "Bakergonj Upazila Livestock Office & Veterinary Hospital",
        "Veterinary Teaching Hospital, BAU",
        "Titlark Petwell Center",
        "Pet Point Gazipur"
    )
    val contact = arrayOf(
        "Contact: 017XXXXXXXX\nEmail: mosharafhos@gmail.com",
        "Contact: 017XXXXXXXX\nEmail: ruksanaahmed@gmail.com",
        "Contact: 018XXXXXXXX\nEmail: abdullaziz@gmail.com",
        "Contact: 019XXXXXXXX\nEmail: muslimar@gmail.com",
        "Contact: 017XXXXXXXX\nEmail: rayantasmir@gmail.com"
    )

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.splash_screen) {
        composable(Routes.splash_screen) {
            SplashScreen(navController = navController)
        }

        composable(Routes.login_screen){
            LoginScreen(navController = navController,
                onButtonClicked = {
                    loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked(navController))
                },
                loginViewModel)
        }

        composable(Routes.option_screen){
            OptionScreen(navController = navController)
        }

        composable(Routes.pet_owner_reg){
            PetOwnerRegScreen(onButtonClicked = {
                signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked(navController))
            },
                signupViewModel)
        }

        composable(Routes.vet_registration){
            VetRegistrationScreen(navController = navController,
                onButtonClicked = {
                    signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked(navController)) },
                signupViewModel)
        }

        composable(Routes.home_screen){
            PetOwnerHomeScreen(navController = navController)
        }

        composable(Routes.about_us){
            AboutUs(navController = navController)
        }

        composable(Routes.cure_screen) {
            CureScreen(navController = navController)
        }
        
        composable(Routes.foster_screen){
            FosterScreen(navController = navController)
        }
        
        composable(Routes.home_screen_vet){
            HomeScreenVet(navController = navController)
        }

        composable(Routes.vet_list){
            VetListScreen( navController = navController)
        }


//        composable(Routes.vet_list){
//            VetListScreen(imageId,names,degrees, navController)
//        }
//
//        composable(route = "vet_details/{index}",
//            arguments = listOf(
//                navArgument(name = "index"){
//                    type = NavType.IntType
//                }
//            )
//        ){
//            index->
//            VetDetails(
//                photos = imageId,
//                names = names,
//                degrees = degrees,
//                work = work,
//                contact = contact,
//                itemIndex = index.arguments?.getInt("index")
//            )
//        }

//        composable(route = Routes.login_screen) {
//            LoginScreen(navController = navController, vm)
//        }
//        composable(route = Routes.option_screen) {
//            OptionScreen(navController = navController)
//        }
//        composable(route = Routes.vet_registration) {
//            VetRegistrationScreen(vm)
//        }
//        composable(route = Routes.pet_owner_reg) {
//            PetOwnerRegScreen(navController)
//        }



    }
}