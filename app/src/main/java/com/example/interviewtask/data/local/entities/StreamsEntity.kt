package com.example.interviewtask.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.interviewtask.data.models.Reason
import com.example.interviewtask.data.models.UserDetails

@Entity(tableName = "streams")
data class StreamsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
//    val userDetails: UserDetails,
    val content: String,
    val time: String,
    val streamModifiedTime: String,
    val formatedTime: String,
    val viewCount: String,
    val url: String,
    val likeCount: String,
    val isCurrentUserLiked: String,
)
