package com.guc3.viajes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.guc3.viajes.R
import com.guc3.viajes.data.Attraction
import com.guc3.viajes.data.AttractionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {

    lateinit var  navController:NavController
    val attractionsList:List<Attraction> by lazy{
        parseAttraction()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
       navController = navHostFragment.navController
    }

    private fun  parseAttraction():List<Attraction>{
        val textFromFile=resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()


        val adapter :JsonAdapter<AttractionsResponse> = moshi.adapter(AttractionsResponse::class.java)

        return adapter.fromJson(textFromFile)!!.attractions
    }
}