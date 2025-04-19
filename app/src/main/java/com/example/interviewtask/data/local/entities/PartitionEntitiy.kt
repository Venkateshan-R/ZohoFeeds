package com.example.interviewtask.data.local.entities

import androidx.room.ColumnInfo
import com.example.interviewtask.data.models.Partition
import kotlin.math.log

data class PartitionEntitiy(
    @ColumnInfo(name = "partition_iid")
    val id: String,
    @ColumnInfo(name = "partition_naoiuygtfdme")
    val name: String,
    val partitionUrl: String,
    @ColumnInfo(name = "partition_url")
    val url: String,
    val isPrivate: String,
    val bgColor: String,
    val logo: String,
){
    fun toPartition()= Partition(id,name,partitionUrl,url,isPrivate,bgColor, logo)

}