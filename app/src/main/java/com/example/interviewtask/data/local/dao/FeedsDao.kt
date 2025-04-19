package com.example.interviewtask.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.interviewtask.data.local.entities.CommentsEntity
import com.example.interviewtask.data.local.entities.StreamsEntity

@Dao
interface FeedsDao {
    @Query("SELECT * FROM streams")
    fun getAllTheStreams(): List<StreamsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTheStreams(streams: List<StreamsEntity>)

    @Query("SELECT * FROM comments WHERE streamId = :streamId")
    fun getComments(streamId: String): List<CommentsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTheComments(streams: List<CommentsEntity>)

}