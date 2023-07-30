package com.vicara.vicara2.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vicara.vicara2.data.local.entity.CardItem

@Dao
interface CardItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(cardItem: CardItem)

    @Delete
    fun deleteCard(cardItem: CardItem)

    @Update
    fun updateCard(cardItem: CardItem)

    @Query("SELECT * FROM kartu WHERE id = :id")
    suspend fun getCardById(id: Int): CardItem

    @Query("SELECT * FROM kartu")
    suspend fun getAllCard(): List<CardItem>
}