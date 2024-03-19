package com.example.catsanddogs.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Percent
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.catsanddogs.LoginViewModel
import com.example.catsanddogs.R
import com.example.catsanddogs.data.ProfileUiState
import com.example.catsanddogs.data.ProfileViewModel
import com.example.catsanddogs.data.SignupViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel(),
    profileViewModel: ProfileViewModel = viewModel(),
    signupViewModel: SignupViewModel = viewModel(),
    fromLogIn:Boolean = false,
//    photos: Array<Int>,
//    names: Array<String>,
//    degrees: Array<String>,
//    work: Array<String>,
//    contact: Array<String>,
    navController: NavController
//    itemIndex: Int?
) {
    val email= if(!fromLogIn) signupViewModel.loginUIState.value.email else loginViewModel.loginUIState.value.email
    val profiles=profileViewModel.stateList
    var profile= mutableStateOf(ProfileUiState())
    for(p in profiles.value){
        if(p.email==email){
            profile.value=p
            Log.d("MySelfTag", p.toString())
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        //modifier = Modifier.fillMaxSize()
    ) {
        val items = listOf(
            NavigationBarItems(
                route = Routes.home_screen,
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
            NavigationBarItems(
                route = Routes.profile_screen,
                title = "Profile",
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person
            ),
            NavigationBarItems(
                route = Routes.login_screen,
                title = "Log Out",
                selectedIcon = Icons.AutoMirrored.Filled.ExitToApp,
                unselectedIcon = Icons.AutoMirrored.Outlined.ExitToApp
            ),
            NavigationBarItems(
                route = Routes.about_us,
                title = "About Us",
                selectedIcon = Icons.Filled.Info,
                unselectedIcon = Icons.Outlined.Info
            )
        )

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }

        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(16.dp))
                    items.forEachIndexed { index, navigationBarItems ->
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    text = navigationBarItems.title,
                                    fontFamily = FontFamily.Serif
                                )
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                if (index == 2) {
                                    navController.popBackStack(
                                        route = navigationBarItems.route,
                                        inclusive = false
                                    )
                                } else {
                                    navController.navigate(navigationBarItems.route)
                                }

                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        navigationBarItems.selectedIcon
                                    } else navigationBarItems.unselectedIcon,
                                    contentDescription = navigationBarItems.title
                                )
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            },
            drawerState = drawerState
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    //.background(Color(0xFFFFDBE9))
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(text = "Cats and Dogs", fontFamily = FontFamily.Serif)

                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "menu"
                                )
                            }

                        },
                        scrollBehavior = scrollBehavior
                    )
                }
            ) {it ->
                Column(
                    modifier
                        .fillMaxSize()
                        .padding(it),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Box(
                        modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = null,
                            modifier = Modifier.clip(RoundedCornerShape(16.dp))
                                .size(120.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    ){

                        details(
                            imageVector = Icons.Default.Person,
                            title ="Name:",
                            content = "${profile.value.firstName} ${profile.value.lastName}"
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        details(
                            imageVector = Icons.Default.Person,
                            title ="Email:",
                            content = "${profile.value.email}"
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        details(
                            imageVector = Icons.Default.Person,
                            title ="Contact Number:",
                            content = "${profile.value.contactNo}"
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        details(
                            imageVector = Icons.Default.Person,
                            title ="Using as:",
                            content = "${profile.value.role}"
                        )
                    }


                }
            }
        }
    }
}

@Composable
fun details(
    imageVector: ImageVector, title: String, content: String
){

    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = imageVector, contentDescription = null)

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Text(
                text = content,
                fontSize = 15.sp
            )
        }
    }

}

