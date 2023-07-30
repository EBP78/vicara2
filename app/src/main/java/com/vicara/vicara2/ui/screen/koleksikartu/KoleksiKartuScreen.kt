package com.vicara.vicara2.ui.screen.koleksikartu

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vicara.vicara2.R
import com.vicara.vicara2.data.local.entity.CardItem
import com.vicara.vicara2.ui.component.Item
import com.vicara.vicara2.ui.component.Overlay
import com.vicara.vicara2.ui.component.Title
import com.vicara.vicara2.utils.SpeechUtils
import com.vicara.vicara2.utils.ViewModelFactory
import com.vicara.vicara2.utils.isFileExist

@Composable
fun KoleksiKartuScreen(
    editKartu: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: KoleksiKartuViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
    speechUtils: SpeechUtils = SpeechUtils(LocalContext.current)
) {
    val cardItems by viewModel.cardItems
    val show by viewModel.show
    val isLogin by viewModel.isLogin
    val kartu by viewModel.kartu

    LaunchedEffect(key1 = true){
        viewModel.getAll()
        viewModel.closePopup()
    }
    Column {
        Title(text = "Koleksi Kartu")
        LazyVerticalGrid(columns = GridCells.Adaptive(150.dp), contentPadding = PaddingValues(15.dp)){
            items(cardItems){ cardItem ->
                Item(text = cardItem.text,
                    onClick = {
                        speechUtils.speakOut(cardItem.text)
                        if (isLogin){
                            viewModel.findCard(cardItem.id)
                            viewModel.showPopup()
                        }
                    },
                    imagePath = cardItem.imagePath,
                    modifier = Modifier.padding(10.dp))
            }
        }
    }
    
    CardPop(
        card = kartu,
        show = show,
        delete = { viewModel.deleteCard(kartu) },
        edit = { editKartu(kartu.id) },
        closeOverlay = { viewModel.closePopup() })
}


@Composable
fun CardPop(
    card: CardItem,
    show: Boolean,
    delete: () -> Unit,
    edit: () -> Unit,
    closeOverlay: () -> Unit,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    if (show){
        Overlay(
            isVisible = show,
            content = {
                Column(modifier = Modifier.padding(10.dp).width(200.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    if (isFileExist(card.imagePath)){
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(card.imagePath)
                                .build(),
                            contentDescription = "image",
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                        )
                    } else {
                        Image(
                            painterResource(id = R.drawable.gallery),
                            contentDescription = "image",
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                        )
                    }
                    Text(text = card.text)
                    Row {
                        Button(
                            onClick = {
                                delete()
                                closeOverlay()
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                            modifier = Modifier.width(100.dp)
                        ) {
                            Text(text = "DELETE", color = Color.White)
                        }
                        Button(
                            onClick = {
                                edit()
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            modifier = Modifier.width(100.dp)
                        ) {
                            Text(text = "EDIT", color = Color.White)
                        }
                    }
                }
            },
            onClickOutside = { closeOverlay() },
            modifier = modifier
        )
    }
}