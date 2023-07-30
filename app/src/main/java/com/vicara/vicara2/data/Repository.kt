package com.vicara.vicara2.data

import android.content.Context
import com.vicara.vicara2.data.local.entity.CardItem
import com.vicara.vicara2.data.local.room.CardItemDao
import com.vicara.vicara2.data.local.room.CardItemDatabase
import com.vicara.vicara2.data.preference.UserPreference
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository (private val dao: CardItemDao, private val preference: UserPreference, private val executor: ExecutorService) {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(context: Context): Repository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = CardItemDatabase.getDatabase(context)
                    instance = Repository(
                        database.cardItemDao(),
                        UserPreference(context),
                        Executors.newSingleThreadExecutor()
                    )
                }
                return instance as Repository
            }

        }
    }

    suspend fun getAllCard(): List<CardItem>{
        return dao.getAllCard()
    }

    suspend fun getCardById(id: Int): CardItem{
        return dao.getCardById(id)
    }

    fun insertCard(cardItem: CardItem){
        executor.execute {
            dao.insertCard(cardItem)
        }
    }

    fun deleteCard(cardItem: CardItem){
        executor.execute {
            dao.deleteCard(cardItem)
        }
    }

    fun updateCard(cardItem: CardItem){
        executor.execute {
            dao.updateCard(cardItem)
        }
    }

    fun login(){
        preference.login()
    }

    fun logout(){
        preference.logout()
    }

    fun isLogin() : Boolean{
        return preference.checkIsLogin()
    }

}