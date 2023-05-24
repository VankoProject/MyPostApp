package com.example.mypostapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostDbEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val description: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val color: Int,
    @ColumnInfo(name = "creating_date")
    val creatingDate: Long

)
