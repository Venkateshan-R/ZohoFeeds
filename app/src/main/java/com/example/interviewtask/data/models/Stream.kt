package com.example.interviewtask.data.models

import androidx.compose.runtime.Stable

data class Stream(
    val id: String,
    val userDetails: UserDetails,
    val partition: Partition,
    val content: String,
    val time: String,
    val streamModifiedTime: String,
    val formatedTime: String,
    val viewCount: String,
    val comments: List<Comment>,
    val url: String,
    val likeCount: String,
    val isCurrentUserLiked: String,
    val tags: List<String>,
    val attachments: List<String>,
    val reason: Reason,
)