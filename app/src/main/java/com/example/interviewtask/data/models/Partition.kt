package com.example.interviewtask.data.models

import androidx.room.ColumnInfo
import com.example.interviewtask.data.local.entities.PartitionEntitiy

data class Partition(
    val id: String,
    val name: String,
    val partitionUrl: String,
    val url: String,
    val isPrivate: String,
    val bgColor: String,
    val logo: String,
){
    fun toPartitionEntity()=PartitionEntitiy(id,name,partitionUrl,url,isPrivate,bgColor,logo)
}