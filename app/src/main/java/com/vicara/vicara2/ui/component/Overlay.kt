package com.vicara.vicara2.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Overlay(
    isVisible: Boolean,
    content: @Composable () -> Unit,
    onClickOutside : ()-> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize().clickable { onClickOutside() },
        color = Color.Black.copy(alpha = 0.6f)
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(1000, easing = LinearEasing)),
            exit = fadeOut(animationSpec = tween(1000, easing = LinearEasing))
        ) {
            Box(modifier = Modifier.fillMaxSize()){
                Surface(
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center).shadow(10.dp, RoundedCornerShape(5.dp))
                ) {
                    content()
                }
            }
        }
    }
}