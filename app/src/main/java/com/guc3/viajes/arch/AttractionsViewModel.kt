package com.guc3.viajes.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guc3.viajes.data.Attraction

class AttractionsViewModel:ViewModel() {

    //la idea de esta variable es que atraves de esta clase (AttractionsViewModel)
    // sera la unica forma de acceder al repositorio

    //instanciamos  el repositorio
    private  val repostory=AttractionsRepostory()
    //creamos la varable de la lista de attracciones
    val attractionListLiveData= MutableLiveData<List<Attraction>>()

    fun init(context: Context){
        //ingresamos  el contexto a la funcion parseAttraction del repositorio
        //llenamos la variable "attractionList" con los datos del json
        val attractionList=repostory.parseAttraction(context)
        //llenamos al variable "attractionListLiveData" con la lista  del repositorio
        attractionListLiveData.postValue(attractionList)
        //attractionListLiveData.value=attractionList

    }


}