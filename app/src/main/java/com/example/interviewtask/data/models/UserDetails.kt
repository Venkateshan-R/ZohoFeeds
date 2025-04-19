package com.example.interviewtask.data.models

import androidx.room.ColumnInfo
import com.example.interviewtask.data.local.entities.UserDetailsEntity

data class UserDetails(
    val id: String,
    val name: String,
    val imageUrl: String,
){
    fun toUserDetailsEntity() = UserDetailsEntity(id,name,imageUrl)
}