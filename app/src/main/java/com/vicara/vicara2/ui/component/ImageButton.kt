package com.vicara.vicara2.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageButton(
    resDrawable: Int,
    onClick: ()-> Unit,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        border = BorderStroke(1.dp, Color.White),
        backgroundColor = color,
        modifier = modifier.clickable {
            onClick()
        }
    ) {
        Box (modifier = modifier) {
            Image(
                painterResource(id = resDrawable),
                contentDescription = "ImageButton",
                modifier = Modifier
                    .padding(25.dp, 5.dp, 25.dp, 5.dp)
            )
        }
    }
}