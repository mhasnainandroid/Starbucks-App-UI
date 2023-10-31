package com.elexoft.starbucks.navigation

sealed class Routes(val route:String){
    object  Splash:Routes("splash")
    object  Home:Routes("home")
    object  ProductDetails:Routes("productdetails")
}
