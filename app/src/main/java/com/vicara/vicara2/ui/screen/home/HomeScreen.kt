package com.vicara.vicara2.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vicara.vicara2.R
import com.vicara.vicara2.ui.component.Item

@Composable
fun HomeScreen(
    toAddkartu: () -> Unit,
    toKoleksiKartu: () -> Unit,
    toSusunKartu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()){
        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // todo : buat preference untuk tau pas login
            // todo : buat login
            // todo : ketika tidak login buat kartu hilang dan di koleksi kartu tidak bisa edit dan hapus
           Item(text = "Buat Kartu", useDrawable = true, resDrawable = R.drawable.buat_kartu, onClick = {toAddkartu()})
           Item(text = "Koleksi Kartu", useDrawable = true, resDrawable = R.drawable.koleksi_kartu, onClick = {toKoleksiKartu()})
           Item(text = "Susun Kartu", useDrawable = true, resDrawable = R.drawable.susun_kartu, onClick = {toSusunKartu()})
        }
    }
}