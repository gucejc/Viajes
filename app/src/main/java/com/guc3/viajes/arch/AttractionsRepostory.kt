package com.guc3.viajes.arch

import android.content.Context
import com.guc3.viajes.R
import com.guc3.viajes.data.Attraction
import com.guc3.viajes.data.AttractionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class AttractionsRepostory {
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun  parseAttraction(context: Context):ArrayList<Attraction>{
        val textFromFile=context.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        val adapter : JsonAdapter<AttractionsResponse> = moshi.adapter(AttractionsResponse::class.java)
        return adapter.fromJson(textFromFile)!!.attractions as ArrayList<Attraction>
    }
}