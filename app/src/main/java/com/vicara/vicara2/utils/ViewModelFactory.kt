package com.vicara.vicara2.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vicara.vicara2.data.Repository
import com.vicara.vicara2.ui.screen.addkartu.AddKartuViewModel
import com.vicara.vicara2.ui.screen.koleksikartu.KoleksiKartuViewModel
import com.vicara.vicara2.ui.screen.susunkartu.SusunKartuViewModel

class ViewModelFactory private constructor(private val repository: Repository) : ViewModelProvider.Factory {

    companion object {
        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
        instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Repository.getInstance(context)
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(AddKartuViewModel::class.java) -> {
                AddKartuViewModel(repository) as T
            }
            modelClass.isAssignableFrom(KoleksiKartuViewModel::class.java) -> {
                KoleksiKartuViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SusunKartuViewModel::class.java) -> {
                SusunKartuViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}