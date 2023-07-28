package com.vicara.vicara2.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageChooseItem (
    resDrawable: Int,
    text: String,
    onClick: ()-> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(50.dp)
            .clickable { onClick() }
    ) {
        Image(
            painterResource(id = resDrawable),
            contentDescription = "image",
            modifier = Modifier.fillMaxHeight()
        )
        Text(text = text)
    }
}