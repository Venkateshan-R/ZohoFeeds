package com.example.interviewtask.data.models

data class Comment(
    val id: String,
    val time: String,
    val formatedTime: String,
    val content: String,
    val likeCount: String,
    val replyCount: String,
    val isCurrentUserLiked: String,
    val userDetails: UserDetails,
)