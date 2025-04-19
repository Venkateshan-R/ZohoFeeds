package com.example.interviewtask.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.interviewtask.data.local.entities.StreamsEntity
import com.example.interviewtask.data.models.Stream

@Dao
interface FeedsDao {
    @Query("SELECT * FROM streams_table")
    suspend fun getAllTheStreams(): List<StreamsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertAllTheStreams(streams: List<StreamsEntity>)

     @Query("SELECT * FROM streams_table WHERE id = :streamId")
     suspend fun getStreamById(streamId:String):StreamsEntity?

     @Query("DELETE from streams_table")
     suspend fun clearAllTheStream()

}