package com.guc3.viajes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.guc3.viajes.data.Attraction
import com.guc3.viajes.databinding.FragmentAttractionDetailBinding
import com.guc3.viajes.databinding.FragmentHomeBinding
import com.guc3.viajes.ui.fragment.home.HomeFragmentAdapter
import com.guc3.viajes.ui.fragment.home.HomeFragmentDirections
import com.squareup.picasso.Picasso

class AttractionDetailFragment : BaseFragment() {
    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding get() = _binding!!

    private val safeArgs:AttractionDetailFragmentArgs by navArgs()

    private val attraction:Attraction by lazy{
        attractions.find { it.id== safeArgs.attractionId }!! //forzamos a que no  sea nulo!!

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitileDetail.text=attraction.title
        binding.tvDescriptionDetail.text=attraction.description
        //todo cargando la imagen via picasso
         Picasso.get().load(attraction.image_url).into(binding.ivHeaderDetail)
        binding.tvVistasDetail.text=attraction.months_to_visit
        binding.tvNumberofacts.text="${attraction.facts.size} facts"
        binding.tvNumberofacts.setOnClickListener {
            //todo
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}