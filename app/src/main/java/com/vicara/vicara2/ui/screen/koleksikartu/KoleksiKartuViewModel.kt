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

    private val _kartu = mutableStateOf(CardItem(0,"",""))
    val kartu : State<CardItem> get() = _kartu

    fun getAll(){
        viewModelScope.launch {
            _cardItems.value = repository.getAllCard()
        }
    }

    fun findCard(id: Int){
        viewModelScope.launch {
            _kartu.value = repository.getCardById(id)
        }
    }

    fun deleteCard(cardItem: CardItem){
        viewModelScope.launch {
            repository.deleteCard(cardItem)
            getAll()
        }
    }

    private val _isLogin = mutableStateOf(repository.isLogin())
    val isLogin : State<Boolean> get() = _isLogin

    private val _show = mutableStateOf(false)
    val show : State<Boolean> get() = _show

    fun showPopup(){
        _show.value = true
    }

    fun closePopup(){
        _show.value = false
    }
}