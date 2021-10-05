package com.guc3.viajes.ui.fragment.details

import com.airbnb.epoxy.EpoxyController
import com.guc3.viajes.R
import com.guc3.viajes.databinding.ModelHeaderImageBinding
import com.guc3.viajes.ui.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class DetailEpoxyController( val imageUrls:List<String> ):EpoxyController (){

    override fun buildModels() {
        imageUrls.forEachIndexed{ index, url ->
            DetailEpoxyModel(url).id(index).addTo(this)
        }
    }

    inner class DetailEpoxyModel(
        val imageUrl:String
    ):ViewBindingKotlinModel<ModelHeaderImageBinding>(R.layout.model_header_image){


        override fun ModelHeaderImageBinding.bind() {
            Picasso.get().load(imageUrl).into(imageView )
        }
    }
}