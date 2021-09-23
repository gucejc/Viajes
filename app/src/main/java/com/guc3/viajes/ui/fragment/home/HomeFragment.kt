package com.guc3.viajes.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.guc3.viajes.databinding.FragmentHomeBinding
import com.guc3.viajes.ui.fragment.BaseFragment

class HomeFragment : BaseFragment() {

    private var _binding:FragmentHomeBinding?=null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding=FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // sin dataBinding
        //val recyclerView:RecyclerView=view.findViewById(R.id.recyclerView)
        //Con Binding
        //val rv=binding.recyclerViewHome

        val homeAdapter= HomeFragmentAdapter{

        }
        binding.recyclerViewHome.adapter=homeAdapter
        //crea un alinea  delgada entre cada cardview
        binding.recyclerViewHome.addItemDecoration(DividerItemDecoration(requireActivity(),RecyclerView.VERTICAL))

        // valor de attractions esta en la clase BaseFragment
        homeAdapter.setData(attractions)



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null

    }
}