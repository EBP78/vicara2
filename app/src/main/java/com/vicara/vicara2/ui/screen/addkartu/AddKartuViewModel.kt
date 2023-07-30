package com.vicara.vicara2.ui.screen.addkartu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicara.vicara2.data.Repository
import com.vicara.vicara2.data.local.entity.CardItem
import kotlinx.coroutines.launch

class AddKartuViewModel (private val repository: Repository) : ViewModel() {

    private val _pickImageShow = mutableStateOf(false)
    val pickImageShow : State<Boolean> get() = _pickImageShow

    fun showPickImage(){
        _pickImageShow.value = true
    }

    fun closePickImage(){
        _pickImageShow.value = false
    }

    private val _imagePath = mutableStateOf("")
    val imagePath : State<String> get() = _imagePath

    fun updateImagePath(newValue: String) {
        _imagePath.value = newValue
    }

    private val _query = mutableStateOf("")
    val query : State<String> get() = _query

    fun updateQuery(newValue: String){
        _query.value = newValue
    }

    fun insertData(cardItem: CardItem){
        // todo : cek jika kosong text / imagepath
        repository.insertCard(cardItem)
    }
}