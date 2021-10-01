package com.guc3.viajes.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guc3.viajes.data.Attraction
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AttractionsViewModel:ViewModel() {

    //la idea de esta variable es que atraves de esta clase (AttractionsViewModel)
    // sera la unica forma de acceder al repositorio

    //instanciamos  el repositorio
    private  val repostory=AttractionsRepostory()
    //creamos la varable de la lista de attracciones
    //es solo para el HomeFragment
    val attractionListLiveData= MutableLiveData<ArrayList<Attraction>>()

    //es solo para el DeatilFragment
    val selectedAttrationLiveData=MutableLiveData<Attraction>()

    //variable para la localización de maps
    val locationSelectedLiveData =MutableLiveData<Attraction>()



    fun init(context: Context){

        viewModelScope.launch {
            delay(5_000)
            //ingresamos  el contexto a la funcion parseAttraction del repositorio
            //llenamos la variable "attractionList" con los datos del json
            val attractionList=repostory.parseAttraction(context)
            //llenamos al variable "attractionListLiveData" con la lista  del repositorio
            // esta valiable va ser observada desde el homefragmnet
            attractionListLiveData.postValue(attractionList)
            //attractionListLiveData.value=attractionList

        }


    }

    //funcion para obtener los datos  del attactionDetailFragment
    fun onAtrractionSelected(attractionId:String){


        val attraction=attractionListLiveData.value?.find {
            it.id ==  attractionId
        }?: return

        selectedAttrationLiveData.postValue(attraction)
    }


}