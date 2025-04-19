package com.example.interviewtask.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.interviewtask.data.local.dao.FeedsDao
import com.example.interviewtask.data.local.entities.CommentsEntity
import com.example.interviewtask.data.local.entities.StreamsEntity


@Database(entities = [StreamsEntity::class, CommentsEntity::class], version = 1, )
abstract class StreamsDatabase : RoomDatabase() {

    abstract fun feedsDao(): FeedsDao

}