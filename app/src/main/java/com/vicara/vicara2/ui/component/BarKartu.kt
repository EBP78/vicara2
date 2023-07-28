package com.vicara.vicara2.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vicara.vicara2.data.local.entity.CardItem

@Composable
fun BarKartu(
    listKartu: List<CardItem>,
    makeSound: (String) -> Unit,
    delete: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier.height(50.dp)
    ) {
        items(
            count = listKartu.size,
            key = {
                it
            },
            itemContent = { index ->
                Item(
                    text = listKartu[index].text,
                    imagePath = listKartu[index].imagePath,
                    onClick = {},
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
            }
        )
    }
}