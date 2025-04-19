package com.example.interviewtask.data.local.entities

import com.example.interviewtask.data.models.Comment
import com.example.interviewtask.data.models.UserDetails

data class CommentEntity(
    val id: String,
    val time: String,
    val formatedTime: String,
    val content: String,
    val likeCount: String,
    val replyCount: String,
    val isCurrentUserLiked: String,
    val userDetails: UserDetails,
) {
    fun toComment() =
        Comment(
            id,
            time,
            formatedTime,
            content,
            likeCount,
            replyCount,
            isCurrentUserLiked,
            userDetails
        )

}