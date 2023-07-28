package com.vicara.vicara2

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.vicara.vicara2.ui.theme.Vicara2Theme
import com.vicara.vicara2.utils.LandscapeUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Vicara2Theme {
                LandscapeUtils(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                Box(modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(id = R.drawable.sky_bg),
                        contentScale = ContentScale.FillBounds
                    )
                    .paint(
                        painterResource(id = R.drawable.grass),
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.BottomCenter
                    ))
                {
                    VicaraApp(application)
                }
            }
        }
    }
}