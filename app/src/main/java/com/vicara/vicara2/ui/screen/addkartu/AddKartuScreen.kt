package com.vicara.vicara2.ui.screen.addkartu

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vicara.vicara2.R
import com.vicara.vicara2.data.local.entity.CardItem
import com.vicara.vicara2.ui.component.ImageChooseItem
import com.vicara.vicara2.ui.component.InputText
import com.vicara.vicara2.ui.component.Overlay
import com.vicara.vicara2.utils.ViewModelFactory
import com.vicara.vicara2.utils.createTempFile
import com.vicara.vicara2.utils.isFileExist
import com.vicara.vicara2.utils.saveFile
import com.vicara.vicara2.utils.uriToFile
import java.io.File

@Composable
fun AddKartuScreen (
    application: Application,
    modifier: Modifier = Modifier,
    backToHome: () -> Unit,
    viewModel: AddKartuViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    ),
    context: Context = LocalContext.current
) {
    val showPickImage by viewModel.pickImageShow
    val imagePath by viewModel.imagePath
    val query by viewModel.query

    Box(modifier = modifier.fillMaxSize()){
        Column (modifier = Modifier.align(Alignment.CenterStart).padding(start = 50.dp)) {
//            Image(
//                painter = rememberImagePainter(data = pathToBitmap(imagePath, context)),
//                contentDescription = "image",
//                alignment = Alignment.Center,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .width(150.dp)
//                    .height(150.dp)
//            )
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
        Column (modifier = Modifier.align(Alignment.CenterEnd).padding(end = 50.dp)) {
            Image(
                painterResource(id = R.drawable.camera),
                contentDescription = "image",
                modifier = Modifier.clickable {
                    viewModel.showPickImage()
                }
                    .width(150.dp)
                    .height(150.dp)
            )
            Image(
                painterResource(id = R.drawable.add),
                contentDescription = "image",
                modifier = Modifier.clickable {
                    val kartu = CardItem(
                        imagePath = imagePath,
                        text = query
                    )
                    viewModel.insertData(kartu)
                    backToHome()
                }
                    .width(150.dp)
                    .height(150.dp)
            )
        }
        AskImage(updateImagePath = {viewModel.updateImagePath(it)}, application = application, show = showPickImage, noPermission = backToHome, closeOverlay = {viewModel.closePickImage()})
    }
}

@Composable
fun AskImage(
    show: Boolean,
    updateImagePath: (String) -> Unit,
    noPermission: () -> Unit,
    closeOverlay: ()-> Unit,
    application: Application,
    context: Context = LocalContext.current
) {
    var currentPhotoPath by remember { mutableStateOf("")}
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK){
                val selectedImg : Uri = result.data?.data as Uri
                val myFile = uriToFile(selectedImg, application, context)
                updateImagePath(myFile.path)
            }
        }
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK){
                val myFile = File(currentPhotoPath)
                saveFile(application, myFile)
                updateImagePath(myFile.path)
            }
        }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted){
            noPermission()
        }
    }
    if (show){
        Overlay(content = {
            Column(modifier = Modifier.padding(10.dp)) {
                LaunchedEffect(key1 = true){
                    if (!allPermissionsGranted(context)){
                        permissionLauncher.launch(REQUIRED_PERMISSIONS[0])
                    }
                }
                ImageChooseItem(
                    resDrawable = R.drawable.camera,
                    text = "Camera",
                    onClick = {
                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        intent.resolveActivity(context.packageManager)

                        createTempFile(application).also {
                            val photoURI: Uri = FileProvider.getUriForFile(
                                context,
                                "com.vicara.vicara2",
                                it
                            )
                            currentPhotoPath = it.absolutePath
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                            cameraLauncher.launch(intent)
                        }
                        closeOverlay()
                    },
                    modifier = Modifier.width(150.dp)
                )
                // TODO : perbaiki lebar dari divider dan samakan lebar dari imagechoose
                Divider(thickness = 1.dp, color = Color.Black, modifier = Modifier.width(150.dp))
                ImageChooseItem(
                    resDrawable = R.drawable.file_manager,
                    text = "File Manager",
                    onClick = {
                        val intent = Intent()
                        intent.action = Intent.ACTION_GET_CONTENT
                        intent.type = "image/*"
                        val chooser = Intent.createChooser(intent, "Choose a Picture")
                        galleryLauncher.launch(chooser)
                        closeOverlay()
                    },
                    modifier = Modifier.width(150.dp)
                )
        } }, isVisible = show, onClickOutside = {closeOverlay()})
    }
}

fun allPermissionsGranted(context: Context) = REQUIRED_PERMISSIONS.all {
    ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
}

val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
