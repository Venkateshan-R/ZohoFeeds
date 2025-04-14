package com.example.interviewtask.ui.utils

import android.content.Context
import android.widget.Toast
import com.example.interviewtask.R
import com.example.interviewtask.data.models.PostModel
import com.google.gson.Gson

fun Context.showToast(message : String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}


fun getDummyData(context: Context): PostModel? {
     val jsonString = context.resources.openRawResource(R.raw.sample_response)
        .bufferedReader().use { it.readText() }

    return Gson().fromJson(jsonString, PostModel::class.java)
}

fun getStream(context: Context) = getDummyData(context)!!.recentStreams.streams.get(0)