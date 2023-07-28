package com.vicara.vicara2.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kartu")
data class CardItem (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0,

    @ColumnInfo(name = "text")
    val text : String,

    @ColumnInfo(name = "imagePath")
    val imagePath : String,
)