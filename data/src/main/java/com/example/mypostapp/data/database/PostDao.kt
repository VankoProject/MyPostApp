package com.example.mypostapp.data.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface PostDao {
    @Query("SELECT * FROM posts")
    fun getAllPosts(): List<PostDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewPostToDb(postDbEntity: PostDbEntity)

    @Delete
    suspend fun deletePostFromDb(postDbEntity: PostDbEntity)
}