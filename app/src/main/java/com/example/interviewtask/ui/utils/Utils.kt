package com.example.interviewtask.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.example.interviewtask.R
import com.example.interviewtask.data.models.FeedsModel
import com.google.gson.Gson

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun getDummyData(context: Context): FeedsModel? {
    val jsonString = context.resources.openRawResource(R.raw.sample_response)
        .bufferedReader().use { it.readText() }

    return Gson().fromJson(jsonString, FeedsModel::class.java)
}

fun getStream(context: Context) = getDummyData(context)!!.recentStreams.streams.get(0)

fun isNetworkAvailable(context: Context): Boolean {
    val networkInfo =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun Float.formatCount() = when {
    this < 1000 -> "${this.toInt()}"
    this < 1000000 -> {
        val formattedText = "%.1f".format(this / 1000)
        (if (formattedText.endsWith(".0")) formattedText.dropLast(2) else formattedText) + "K"
    }

    else -> {
        val formattedText = "%.1f".format(this / 1000000)
        (if (formattedText.endsWith(".0")) formattedText.dropLast(2) else formattedText) + "M"

    }
}.also {
    println("test# $this")
}