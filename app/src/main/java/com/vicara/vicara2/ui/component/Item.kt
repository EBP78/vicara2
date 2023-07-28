package com.vicara.vicara2.ui.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.vicara.vicara2.R
import com.vicara.vicara2.ui.theme.Vicara2Theme
import com.vicara.vicara2.utils.pathToBitmap

@Composable
fun Item(
    text: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black,
    imagePath: String = "",
    useDrawable: Boolean = false,
    resDrawable: Int = R.drawable.gallery,
    context: Context = LocalContext.current
) {
    val interactionSource = remember { MutableInteractionSource() }
    Card(
        modifier = modifier
            .width(150.dp)
            .height(150.dp)
            .clickable (interactionSource = interactionSource , indication = null) { onClick(text) },
        elevation = 10.dp
    ) {
        Column (modifier = modifier){
            if (useDrawable) {
                Image(
                    painterResource(id = resDrawable),
                    contentDescription = "image",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            } else {
                Image(
                    painter = rememberImagePainter(data = pathToBitmap(imagePath, context)),
                    contentDescription = "image",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }
            Text(
                text = text,
                color = textColor,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ItemPreview(){
    Vicara2Theme {
        Item(imagePath = "asd/asdf.png", text = "Bambang irawan", onClick = {})
    }
}