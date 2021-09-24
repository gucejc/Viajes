package com.guc3.viajes.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guc3.viajes.R
import com.guc3.viajes.data.Attraction
import com.guc3.viajes.databinding.ViewHolderAttractionBinding
import com.squareup.picasso.Picasso

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