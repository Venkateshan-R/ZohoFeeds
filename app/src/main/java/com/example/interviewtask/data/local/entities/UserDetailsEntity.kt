package com.example.interviewtask.data.local.entities

import androidx.room.ColumnInfo
import com.example.interviewtask.data.models.UserDetails

data class UserDetailsEntity(
    @ColumnInfo(name = "user_id")
    val id: String,
    @ColumnInfo(name = "user_name")
    val name: String,
    val imageUrl: String,
){
    fun toUserDetails() = UserDetails(id,name,imageUrl)
}