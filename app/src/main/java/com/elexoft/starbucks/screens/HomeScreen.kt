package com.elexoft.starbucks.screens

import android.graphics.drawable.Icon
import android.preference.PreferenceActivity.Header
import android.view.PixelCopy.Request
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.elexoft.starbucks.R
import com.elexoft.starbucks.components.RoundImage
import com.elexoft.starbucks.components.RoundedIconButton
import com.elexoft.starbucks.data.Menu
import com.elexoft.starbucks.data.menuList
import com.elexoft.starbucks.navigation.Routes
import com.elexoft.starbucks.ui.theme.Background
import com.elexoft.starbucks.ui.theme.DarkGray_1
import com.elexoft.starbucks.ui.theme.DarkGreen
import com.elexoft.starbucks.ui.theme.Gray_1
import com.elexoft.starbucks.ui.theme.LightGray
import com.elexoft.starbucks.ui.theme.LightRed_1

@Composable
fun HomeScreen(
    navHostController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        var search by remember {
            mutableStateOf("")
        }
        var selectedIndex by remember {
            mutableStateOf(0)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            TopHeader()
            LazyColumn {
                item {
                    TextDescription()
                    Box {
                        SearchbarScreen(
                            text = search,
                            onValueChange = {
                                search = it
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        RoundedIconButton(
                            icon = R.drawable.filter,
                            backgroundColor = DarkGreen,
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(58.dp)
                        ) {

                        }
                    }

                    //Chips
                    LazyRow(
                        contentPadding = PaddingValues(top = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(menuList.size) {
                            ChipItem(menu = menuList[it], isSelected = selectedIndex == it) {
                                selectedIndex = it
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    //Popular
                    PopularSection(){
                        navHostController.navigate(Routes.ProductDetails.route)
                    }
                }
            }
        }
    }
}

@Composable
private fun PopularSection(
    navigate:()->Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Popular",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "See all",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkGreen
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(4) {
                PopularItem() {
                    navigate()
                }
            }
        }
    }
}

@Composable
private fun PopularItem(
    onClick: () -> Unit
) {
    var isLiked by remember {
        mutableStateOf(false)
    }
    val animatedColor by animateColorAsState(targetValue = if (isLiked)Color.Red else Color.Unspecified,
        label = "",
        animationSpec = tween(durationMillis = 1000)
    )
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .width(220.dp)
            .clickable {
                onClick()
            }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(
                        LightRed_1,
                        RoundedCornerShape(bottomStart = 14.dp, bottomEnd = 14.dp)
                    )
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = null,
                    modifier = Modifier.size(180.dp)
                )
            }

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.chocolate_frappuccino),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Gray
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "$ 220",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.W700,
                        color = DarkGreen
                    )
                    IconButton(onClick = { isLiked = !isLiked }) {
                        androidx.compose.material3.Icon(
                            painter = painterResource(id = R.drawable.love),
                            contentDescription = "Like Button",
                            tint = animatedColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChipItem(
    menu: Menu,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {
    TextButton(
        onClick = onItemClick,
        shape = RoundedCornerShape(22.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) DarkGreen else LightGray,
            contentColor = if (isSelected) Color.White else Color.Black,
        ),

        ) {
        Text(
            text = menu.title, modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 12.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchbarScreen(
    text: String,
    onValueChange: (value: String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = { onValueChange(it) },
        modifier = modifier.clip(RoundedCornerShape(20.dp)),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                color = DarkGray_1,
                fontSize = 16.sp
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Green
        ),
        leadingIcon = {
            androidx.compose.material3.Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = null
            )
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        )
    )
}

@Composable
private fun TextDescription() {
    Text(
        text = stringResource(id = R.string.our_way_of_loving_you_back),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.W600,
            color = Color.Black
        ),
        modifier = Modifier.padding(vertical = 30.dp)
    )
}

@Composable
private fun TopHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundedIconButton(icon = R.drawable.menu) {

        }
        RoundImage(size = 56.dp)
        RoundedIconButton(icon = R.drawable.bag) {

        }
    }
}