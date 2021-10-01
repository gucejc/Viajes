package com.guc3.viajes.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyController
import com.guc3.viajes.R
import com.guc3.viajes.data.Attraction
import com.guc3.viajes.databinding.EpoxyModelHeaderBinding
import com.guc3.viajes.databinding.ViewHolderAttractionBinding
import com.guc3.viajes.ui.epoxy.LoadingEpoxyModel
import com.guc3.viajes.ui.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

// Ahora va ser un controlador epoxy .... dejara se  ser  adaptador  normal

class HomeFragmentController(private val onClickedCallBack:(String) ->Unit): EpoxyController() {

    var isLoading:Boolean=false
        set(value) {
            field = value
            if (field){
                requestModelBuild()
            }
        }

    var attractions=ArrayList<Attraction>()
        set(value) {
        field = value
        isLoading=false
        requestModelBuild()
        }



    override fun buildModels() {
        if(isLoading){
            LoadingEpoxyModel().id("loading_state").addTo(this)
            return
        }

        if(attractions.isEmpty()){
            //todo is attractiosn state
            return
        }

        val firstGroup=attractions.filter { it.title.startsWith("s",true)  || it.title.startsWith("D",true)}

        HeaderEpoxyModel("Vista Reciente").id("Header_1").addTo(this)
        firstGroup.forEach { attraction ->
            AttractionEpoxyModel(attraction,onClickedCallBack).id(attraction.id).addTo(this)
        }

        HeaderEpoxyModel("Todas las Atraciones").id("Header_2").addTo(this)
        attractions.forEach { attraction ->
            AttractionEpoxyModel(attraction,onClickedCallBack).id(attraction.id).addTo(this)
        }
    }



    data class  AttractionEpoxyModel(

        val attraction:Attraction,
        val onClicked:(String) -> Unit
    ):ViewBindingKotlinModel<ViewHolderAttractionBinding>(R.layout.view_holder_attraction) {

        override fun ViewHolderAttractionBinding.bind() {
            tvTitle.text=attraction.title
            //todo cargando la imagen via picasso
            //Picasso.get().load(attraction.image_urls).into(binding.ivHeader)
            Picasso.get().load(attraction.image_url).into(ivHeader)
            tvVistasDelMes.text=attraction.months_to_visit

            //el binding.rootAttraction es  el id  del contraintLayout de la  vista
            rootAttraction.setOnClickListener{
                onClicked(attraction.id)
            }
        }

    }


    data class  HeaderEpoxyModel( val headerText:String):ViewBindingKotlinModel<EpoxyModelHeaderBinding>(R.layout.epoxy_model_header){

        override fun EpoxyModelHeaderBinding.bind() {
            headerTextView.text=headerText
        }

    }


}


/*+++++++++++++++++++++++++++++++++++++++++++++ Clase original +++++++++++++++++++++++++++++++++++++++++++

Esta clase era la original con  un adaptador normal comun y corriente ahora se va a cambiar  por  un controlador epoxy


class HomeFragmentAdapter(private val onClickedCallBack:(String) ->Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val attractions=ArrayList<Attraction>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AttractionViewHolder(parent)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AttractionViewHolder).onBind(attractions[position],onClickedCallBack)
    }

    override fun getItemCount(): Int {
        return attractions.size
    }


    fun setData(attractions :List<Attraction>){

        this.attractions.clear()
        this.attractions.addAll(attractions)
        notifyDataSetChanged()

    }

    inner  class AttractionViewHolder (parent: ViewGroup):RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_attraction,parent,false)

    ){
        private val binding =ViewHolderAttractionBinding.bind(itemView)

        fun onBind(attraction:Attraction, onClicked:(String) ->Unit ) {
            binding.tvTitle.text=attraction.title
            //todo cargando la imagen via picasso
            //Picasso.get().load(attraction.image_urls).into(binding.ivHeader)
            Picasso.get().load(attraction.image_url).into(binding.ivHeader)
            binding.tvVistasDelMes.text=attraction.months_to_visit

            //el binding.rootAttraction es  el id  del contraintLayout de la  vista
            binding.rootAttraction.setOnClickListener{
                onClicked(attraction.id)
            }

        }
    }





}
*/