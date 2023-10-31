package com.elexoft.starbucks.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.elexoft.starbucks.screens.HomeScreen
import com.elexoft.starbucks.screens.ProductDetailScreen
import com.elexoft.starbucks.screens.SplashScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Routes.Splash.route) {

        composable(Routes.Splash.route) {
            SplashScreen(navHostController = navHostController)
        }
        composable(Routes.Home.route,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            }) {
            HomeScreen(navHostController = navHostController)
        }
        composable(Routes.ProductDetails.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }) {
            ProductDetailScreen(navHostController = navHostController)
        }
    }
}