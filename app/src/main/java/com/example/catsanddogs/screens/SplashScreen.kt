package com.example.catsanddogs.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.catsanddogs.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember{
        Animatable(1f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 2.5f,
            animationSpec = tween(
                durationMillis = 1500,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )

        delay(1000L)
        navController.navigate(Routes.login_screen)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.catdogrealbg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            alpha = 0.1f
        )

        Box {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .scale(scale.value)
                        .padding(0.dp, 0.dp, 0.dp, 30.dp)
                )

                Text(
                    text = "Cats and Dogs",
                    fontSize = 42.sp,
                    color = Color(119, 18, 86, 255),
                    modifier = Modifier.padding(0.dp,0.dp,0.dp,0.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Text(
                    text = "Your go-to app for vet consultations and more!",
                    fontSize = 25.sp,
                    modifier = Modifier .padding(0.dp,30.dp,0.dp,0.dp),
                    textAlign = TextAlign.Center,
                    color = Color(226, 87, 181, 255),
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}

