package com.example.interviewtask.data.models



data class RecentStreams(
    val streams: List<Stream>,
)

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

data class UserDetails(
    val id: String,
    val name: String,
    val imageUrl: String,
)

data class Partition(
    val id: String,
    val name: String,
    val partitionUrl: String,
    val url: String,
    val isPrivate: String,
    val bgColor: String,
    val logo: String,
)

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


data class Reason(
    val msg: String,
)

data class Partition2(
    val id: String,
    val name: String,
    val partitionUrl: String,
    val url: String,
    val bgColor: String,
    val logo: String,
)

data class Username61641254(
    val id: String,
    val name: String,
)