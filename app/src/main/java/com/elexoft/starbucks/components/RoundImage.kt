package com.elexoft.starbucks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.elexoft.starbucks.R

@Composable
fun RoundImage(
    modifier: Modifier = Modifier,
    size: Dp = 150.dp,

    ) {
    Image(
        painter = painterResource(
            id = R.drawable.logo
        ),
        contentDescription = "Logo",
        modifier = modifier.size(size)
    )
}