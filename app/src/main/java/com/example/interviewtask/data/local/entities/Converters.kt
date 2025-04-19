package com.example.interviewtask.data.local.entities

import androidx.room.TypeConverter
import com.example.interviewtask.data.models.Comment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun stringToList(value: String?): List<String> {
        //if string is empty, should return only empty list
        return value?.takeUnless { it.isBlank() }?.split(",") ?: listOf()
    }

    @TypeConverter
    fun listToString(list: List<String?>?): String {
        return list?.joinToString(",") ?: ""
    }

    @TypeConverter
    fun listOfCommentsToString(list:List<CommentEntity>?):String{
       return Gson().toJson(list) ?: ""
    }

    @TypeConverter
    fun stringToCommentsList(value : String?): List<CommentEntity> {
        val type = object : TypeToken<List<CommentEntity>>() {}.type
        return Gson().fromJson(value,type) ?: arrayListOf<CommentEntity>()
    }
}

/*fun main() {
    println(Converters().listToString(arrayListOf()))
    val output =Converters().stringToList(Converters().listToString(arrayListOf()))
    println(output.size)
}*/



