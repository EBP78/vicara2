package com.vicara.vicara2.ui.screen.edit

import android.app.Application
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vicara.vicara2.R
import com.vicara.vicara2.data.local.entity.CardItem
import com.vicara.vicara2.ui.component.InputText
import com.vicara.vicara2.ui.screen.addkartu.AddKartuViewModel
import com.vicara.vicara2.ui.screen.addkartu.AskImage
import com.vicara.vicara2.utils.ViewModelFactory
import com.vicara.vicara2.utils.isFileExist

@Composable
fun EditKartuScreen(
    id: Int,
    application: Application,
    modifier: Modifier = Modifier,
    backToKoleksi: () -> Unit,
    viewModel: EditKartuViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
    context: Context = LocalContext.current
) {
    val showPickImage by viewModel.pickImageShow
    val imagePath by viewModel.imagePath
    val query by viewModel.query

    LaunchedEffect(key1 = true){
        viewModel.getData(id)
    }

    Box(modifier = modifier.fillMaxSize()){
        Column (modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(start = 50.dp)) {
            if (isFileExist(imagePath)){
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(imagePath)
                        .build(),
                    contentDescription = "image",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                )
            } else {
                Image(
                    painterResource(id = R.drawable.gallery),
                    contentDescription = "image",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                )
            }
            InputText(query = query, onQueryChange = {viewModel.updateQuery(it)}, placeholder = "kata - kata", modifier = Modifier.width(150.dp))
        }
        Column (modifier = Modifier
            .align(Alignment.CenterEnd)
            .padding(end = 50.dp)) {
            Image(
                painterResource(id = R.drawable.camera),
                contentDescription = "image",
                modifier = Modifier
                    .clickable {
                        viewModel.showPickImage()
                    }
                    .width(150.dp)
                    .height(150.dp)
            )
            Image(
                painterResource(id = R.drawable.add),
                contentDescription = "image",
                modifier = Modifier
                    .clickable {
                        val kartu = CardItem(
                            id = id,
                            imagePath = imagePath,
                            text = query
                        )
                        viewModel.updateData(kartu)
                        backToKoleksi()
                    }
                    .width(150.dp)
                    .height(150.dp)
            )
        }
        AskImage(updateImagePath = {viewModel.updateImagePath(it)}, application = application, show = showPickImage, noPermission = backToKoleksi, closeOverlay = {viewModel.closePickImage()})
    }
}