package com.vicara.vicara2.ui.screen.susunkartu

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicara.vicara2.data.Repository
import com.vicara.vicara2.data.local.entity.CardItem
import kotlinx.coroutines.launch

class SusunKartuViewModel (private val repository: Repository) : ViewModel() {

    private val _cardItems : MutableState<MutableList<CardItem>> = mutableStateOf(mutableListOf())
    val cardItems : State<List<CardItem>> get() = _cardItems

    private val _usedCardList = mutableStateListOf<CardItem>()
    val usedCardList : List<CardItem> = _usedCardList

    fun addUsedList(cardItem: CardItem){
        _usedCardList.add(cardItem)
    }

    fun deleteFromUsedList(){
        if (_usedCardList.isNotEmpty()){
            _usedCardList.removeLast()
        }
    }

    fun getAll(){
        viewModelScope.launch {
            _cardItems.value = repository.getAllCard().toMutableList()
        }
    }

}