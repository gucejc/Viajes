package com.guc3.viajes.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.guc3.viajes.R
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

        //este bloque de  código se ocupa cuando damos click en cualquier imagen de la cardview para que nos envie
        // al fragmento  detail y se  le pasa el attractionId
        val homeAdapter= HomeFragmentAdapter{attractionId ->
            navController.navigate(R.id.action_homeFragment_to_attractionDetailFragment)
            activityViewModel.onAtrractionSelected(attractionId)



            //se puede pasar de unfragmento a otromediante la sigueinte accion , simpre y cuando  exista  la action
            //dentro del navgraph o la conexion  del  navgraph
            //val navDirections= HomeFragmentDirections.actionHomeFragmentToAttractionDetailFragment(attractionId)
            //navController.navigate(navDirections)

        }
        binding.recyclerViewHome.adapter=homeAdapter
        //crea un alinea  delgada entre cada cardview
        binding.recyclerViewHome.addItemDecoration(DividerItemDecoration(requireActivity(),RecyclerView.VERTICAL))

        // valor de attractions esta en la clase BaseFragment
        //ojo ahora los datos lo va a trae de viewModel y de su lista que se crea
        //y cada vez que exista una actualizacion de datos se va ejecutar este codigo
        //esta sección va observar cambios de la lista
        activityViewModel.attractionListLiveData.observe(viewLifecycleOwner){attractions ->
            homeAdapter.setData(attractions)
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }
}