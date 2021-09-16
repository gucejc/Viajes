package com.guc3.viajes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.guc3.viajes.R
import com.guc3.viajes.databinding.FragmentHomeBinding

class HomeFragment :BaseFragment() {

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
        val rv=binding.recyclerViewHome


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null

    }
}