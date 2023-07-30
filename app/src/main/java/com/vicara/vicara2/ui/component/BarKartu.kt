package com.vicara.vicara2.ui.component

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.vicara.vicara2.R
import com.vicara.vicara2.data.local.entity.CardItem

@Composable
fun BarKartu(
    listKartu: List<CardItem>,
    makeSound: (String) -> Unit,
    delete: () -> Unit,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    Row (
        modifier = modifier.height(50.dp)
    ){
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = modifier.fillMaxWidth(0.6f)
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
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(start = 5.dp, end = 5.dp)
        ) {
            ImageButton(
                resDrawable = R.drawable.baseline_volume_up_24,
                onClick = {
                    var text = ""
                    listKartu.map { cardItem ->
                        text += cardItem.text + " "
                    }
                    makeSound(text)
                },
                color = Color.Green,
            )
            ImageButton(
                resDrawable = R.drawable.baseline_send_24,
                onClick = {
                    var text = ""
                    listKartu.map { cardItem ->
                        text += cardItem.text + " "
                    }
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, text)
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent)
                },
                color = Color.Blue
            )
            ImageButton(
                resDrawable = R.drawable.baseline_backspace_24,
                onClick = {
                    delete()
                },
                color = Color.Red
            )
        }
    }

}