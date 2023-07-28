package com.vicara.vicara2.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun ImageButton(
    resDrawable: Int,
    onClick: ()-> Unit,
    modifier: Modifier = Modifier
) {
    // todo : buat card menerima warna dan juga buat ukuran serta border
    Card(
        modifier = modifier.clickable {
            onClick()
        }
    ) {
        Image(painterResource(id = resDrawable), contentDescription = "ImageButton")
    }
}