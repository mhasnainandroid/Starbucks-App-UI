package com.elexoft.starbucks.data

data class Menu(
    val id:Int,
    val title:String
)
val menuList = listOf(
    Menu(id = 1, title = "All"),
    Menu(id = 2, title = "Coffee"),
    Menu(id = 3, title = "Tea"),
    Menu(id = 4, title = "Drinks"),
)