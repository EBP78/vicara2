package com.vicara.vicara2.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vicara.vicara2.data.local.entity.CardItem

@Database(
    entities = [CardItem::class],
    version = 1,
    exportSchema = false
)
abstract class CardItemDatabase : RoomDatabase() {
    abstract fun cardItemDao() : CardItemDao

    companion object {
        private var INSTANCE: CardItemDatabase? = null

        fun getDatabase(context: Context): CardItemDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CardItemDatabase::class.java, "card_item_database.db"
                )
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}