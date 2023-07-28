package com.vicara.vicara2.ui.screen.koleksikartu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicara.vicara2.data.Repository
import com.vicara.vicara2.data.local.entity.CardItem
import kotlinx.coroutines.launch

class KoleksiKartuViewModel(private val repository: Repository) : ViewModel() {

    private val list : List<CardItem> = mutableListOf()
    private val _cardItems = mutableStateOf(list)
    val cardItems : State<List<CardItem>> get() = _cardItems

    fun getAll(){
        viewModelScope.launch {
            _cardItems.value = repository.getAllCard()
        }
    }
}