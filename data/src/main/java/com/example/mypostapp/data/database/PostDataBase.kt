package com.example.mypostapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [PostDbEntity::class],
    exportSchema = false
)
abstract class PostDataBase : RoomDatabase() {

    abstract fun getPostDao(): PostDao

    companion object {
        private var db: PostDataBase? = null
        private const val DB_NAME = "main.db"

        @Synchronized
        fun getInstance(context: Context): PostDataBase {
            return db ?: buildDatabase(context).also { db = it }
        }

        private fun buildDatabase(context: Context): PostDataBase {
            return Room.databaseBuilder(
                context,
                PostDataBase::class.java,
                DB_NAME
            ).build()
        }
    }
}