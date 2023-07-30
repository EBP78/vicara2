package com.vicara.vicara2.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vicara.vicara2.R
import com.vicara.vicara2.ui.component.InputText
import com.vicara.vicara2.ui.component.Item
import com.vicara.vicara2.ui.component.Overlay
import com.vicara.vicara2.utils.ViewModelFactory

@Composable
fun HomeScreen(
    toAddkartu: () -> Unit,
    toKoleksiKartu: () -> Unit,
    toSusunKartu: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
) {
    val isLogin by viewModel.isLogin
    val show by viewModel.show

    Box(modifier = modifier.fillMaxSize()){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(isLogin){
                Item(text = "Buat Kartu", useDrawable = true, resDrawable = R.drawable.buat_kartu, onClick = {toAddkartu()})
            }
            Item(text = "Koleksi Kartu", useDrawable = true, resDrawable = R.drawable.koleksi_kartu, onClick = {toKoleksiKartu()})
            Item(text = "Susun Kartu", useDrawable = true, resDrawable = R.drawable.susun_kartu, onClick = {toSusunKartu()})
        }
        Image(
            painterResource(id = R.drawable.vicara),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(120.dp)
                .align(Alignment.BottomCenter)
        )
        Image(
            painterResource(id = R.drawable.user),
            contentDescription = "profile",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .height(75.dp)
                .align(Alignment.TopEnd)
                .clickable {
                    viewModel.showPopup()
                }
                .padding(10.dp)
        )
        LoginPop(
            isLogin = isLogin,
            show = show,
            login = {
                viewModel.login()
                viewModel.closePopup()
            },
            logout = {
                viewModel.logout()
                viewModel.closePopup()
            },
            closeOverlay = { viewModel.closePopup() })
    }
}

@Composable
fun LoginPop(
    isLogin: Boolean,
    show: Boolean,
    login: () -> Unit,
    logout: () -> Unit,
    closeOverlay: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (show) {
        Overlay(
            isVisible = show,
            content = {
                Column(modifier = Modifier.padding(10.dp).width(250.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    if (isLogin){
                        Text(text = "Ganti Password", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                        InputText(query = "", onQueryChange = {}, placeholder = "Password", isPassword = true)
                        InputText(query = "", onQueryChange = {}, placeholder = "Retype Password", isPassword = true)
                        Row (horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                            Button(
                                onClick = { logout() },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                            ) {
                                Text(text = "Logout", color = Color.White)
                            }
                            Button(onClick = {  },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
                            ) {
                                Text(text = "Submit", color = Color.White)
                            }
                        }
                    } else {
                        Text(text = "Login")
                        InputText(query = "", onQueryChange = {}, placeholder = "Password", isPassword = true, modifier = Modifier.width(250.dp))
                        Button(onClick = { login() },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
                        ) {
                            Text(text = "Submit", color = Color.White)
                        }
                    }
                }
            },
            onClickOutside = { closeOverlay() },
            modifier = modifier
        )
    }
}