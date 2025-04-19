package com.example.interviewtask.data.local.entities

import com.example.interviewtask.data.models.Reason

data class ReasonEntity(
    val msg: String,
){
    fun toReason() = Reason(msg)
}