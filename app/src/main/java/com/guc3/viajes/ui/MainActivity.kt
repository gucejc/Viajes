package com.guc3.viajes.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.guc3.viajes.R
import com.guc3.viajes.arch.AttractionsViewModel
import com.guc3.viajes.data.Attraction
import com.guc3.viajes.data.AttractionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {

    lateinit var  navController:NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    //creamos o instanciamos la variable de viewModel
    val viewModel:AttractionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
       navController = navHostFragment.navController

        //con estas 2 lineas agregamos en la barra de navegacion el boton de regreso
        //unicamnete en el fragmento  de detalle ojo
        //aunque no funciona , solo es la visualizacion
        appBarConfiguration= AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfiguration)

        // ejecutamos la funcion init (inicial del viewmodel) y pasamos el contexto  para que realiza la
        //creacion de laista de los datos json
        viewModel.init(this)

        // aqui vamos a ejecutar  el lanzamiento del  intent de google mapas para que abra la app(Maps)
        // y se vizualice  la localización
        // con  esto podemos decir que  se pueden pasar los datos  de un fragmento a un activity por medio del observe
        viewModel.locationSelectedLiveData.observe(this){attraction ->
            val uri = Uri.parse("geo:${attraction.location.latitude},${attraction.location.longitude}?z=9&q=${attraction.title}")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

        }



    }

    //esta funcion sirve para activar el boton de regreso dentro de la barra de superior
    //este boton solo se activa en el fragmento de detalle
    override fun onSupportNavigateUp():Boolean{
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }


}