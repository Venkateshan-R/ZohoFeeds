package com.example.interviewtask.data.models

import com.example.interviewtask.data.local.entities.ReasonEntity

data class Reason(
    val msg: String,
) {
    fun toReasonEntity() = ReasonEntity(msg)
}