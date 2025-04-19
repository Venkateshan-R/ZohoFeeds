package com.example.interviewtask.data.models

import com.example.interviewtask.data.local.entities.CommentEntity

data class Comment(
    val id: String,
    val time: String,
    val formatedTime: String,
    val content: String,
    val likeCount: String,
    val replyCount: String,
    val isCurrentUserLiked: String,
    val userDetails: UserDetails,
){
    fun toCommentEntity()=CommentEntity(id,time,formatedTime,content,likeCount,replyCount,isCurrentUserLiked,userDetails)
}