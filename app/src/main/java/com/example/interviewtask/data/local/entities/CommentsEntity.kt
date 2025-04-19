package com.example.interviewtask.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.interviewtask.data.models.UserDetails

@Entity(tableName = "comments")
data class CommentsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val streamId : String,
    val time: String,
    val formatedTime: String,
    val content: String,
    val likeCount: String,
    val replyCount: String,
    val isCurrentUserLiked: String,
//    val userDetails: UserDetails,
)

