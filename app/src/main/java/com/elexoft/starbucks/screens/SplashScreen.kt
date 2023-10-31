package com.elexoft.starbucks.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.navigation.NavHostController
import com.elexoft.starbucks.components.RoundImage
import com.elexoft.starbucks.navigation.Routes
import com.elexoft.starbucks.ui.theme.Background
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navHostController: NavHostController
) {
    var startingAplha by remember {
        mutableStateOf(0f)
    }
    val animatedAlpha = animateFloatAsState(
        targetValue = startingAplha,
        animationSpec = tween(durationMillis = 2000), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        RoundImage(modifier = Modifier.alpha(animatedAlpha.value))

        LaunchedEffect(true) {
            startingAplha = 1f
            delay(3000)
            navHostController.navigate(Routes.Home.route) {
                popUpTo(Routes.Splash.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }
}