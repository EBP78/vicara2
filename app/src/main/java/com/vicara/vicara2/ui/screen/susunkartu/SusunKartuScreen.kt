package com.vicara.vicara2.ui.screen.susunkartu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vicara.vicara2.ui.component.BarKartu
import com.vicara.vicara2.ui.component.Item
import com.vicara.vicara2.ui.component.Title
import com.vicara.vicara2.utils.SpeechUtils
import com.vicara.vicara2.utils.ViewModelFactory

@Composable
fun SusunKartuScreen (
    modifier: Modifier = Modifier,
    viewModel: SusunKartuViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
    speechUtils: SpeechUtils = SpeechUtils(LocalContext.current)
) {
    val cardItems by viewModel.cardItems
    val usedCardList = viewModel.usedCardList

    LaunchedEffect(key1 = true){
        viewModel.getAll()
    }
    
    Column {
        Title(text = "Susun Kartu")
        BarKartu(
            listKartu = usedCardList,
            makeSound = {
                speechUtils.speakOut(it)
            },
            delete = {
                viewModel.deleteFromUsedList()
            }
        )

        
        LazyVerticalGrid(columns = GridCells.Adaptive(150.dp), contentPadding = PaddingValues(15.dp)){
            items(
                count = cardItems.size,
                key = {
                    cardItems[it].id
                },
                itemContent = { index ->
                    Item(
                        text = cardItems[index].text,
                        onClick = {
                            viewModel.addUsedList(cardItems[index])
                        },
                        imagePath = cardItems[index].imagePath,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            )
        }
    }
}