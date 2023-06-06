package com.example.mypostapp.data.database

import androidx.room.*

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<PostDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewPostToDb(postDbEntity: PostDbEntity)

    @Delete
    suspend fun deletePostFromDb(postDbEntity: PostDbEntity)
}