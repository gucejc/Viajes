package com.guc3.viajes.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.guc3.viajes.R
import com.guc3.viajes.data.Attraction
import com.guc3.viajes.databinding.FragmentAttractionDetailBinding
import com.guc3.viajes.databinding.FragmentHomeBinding
import com.guc3.viajes.ui.fragment.home.HomeFragmentAdapter
import com.guc3.viajes.ui.fragment.home.HomeFragmentDirections
import com.squareup.picasso.Picasso
import java.util.zip.Inflater

class AttractionDetailFragment : BaseFragment() {
    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //activamos el menu
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // aqui llamamos al observador para que tenga los datos de attraction
        activityViewModel.selectedAttrationLiveData.observe(viewLifecycleOwner){ attraction ->

            binding.tvTitileDetail.text=attraction.title
            binding.tvDescriptionDetail.text=attraction.description
            //todo cargando la imagen via picasso
            Picasso.get().load(attraction.image_url).into(binding.ivHeaderDetail)
            binding.tvVistasDetail.text=attraction.months_to_visit
            binding.tvNumberofacts.text="${attraction.facts.size} facts"
            binding.tvNumberofacts.setOnClickListener {

                val stringBuilder = StringBuilder("")

                attraction.facts.forEach {
                    // el \u2022 es un corchete
                    stringBuilder.append("\u2022 $it")
                    stringBuilder.append("\n\n")
                }

                //truncamos el mensaje hasta que encuentre el salto de linea
                val message=stringBuilder.toString().substring(0,stringBuilder.toString().lastIndexOf("\n\n"))

                AlertDialog.Builder(requireContext(),R.style.MyDialog)
                    .setTitle("${attraction.title} facts")
                    .setMessage(message)
                    .setPositiveButton("ok"){ dialog,which ->
                        dialog.dismiss()
                    }
                    .setCancelable(false)
                    .show()
            }// fn de setOnClickListener

        }



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_attraction_detail,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            //este valor sale del xml de menu es el nombre  item que creamos
            R.id.menuItemLocation ->{

                // con esto vamos a ver la localización de un lugar pero se abrira dentro de la misma app viajes
                //recojemos los datos  de la attraction por medio del viewmodel y posterior obtenemos los datos
                //especificos  de la localización(lat y longitud)
                val attraction=activityViewModel.selectedAttrationLiveData.value ?: return true
                activityViewModel.locationSelectedLiveData.postValue(attraction)
                true

                //?z=9&q=${attraction.title} esto es para darle zoom a la imagen del mapa  y agregar el titulo a textview de buscar dentrod el mapa
                // te pinta en rojo la zona qie estamos buscando
                //esto se ocupa para lanzar  por medio de  un intent la localizaciond el  un lugar por medio de google maps
                // ojo con esto se abre googe maps  con los datos de longitud  y latitude
                //
                //val attraction=activityViewModel.selectedAttrationLiveData.value ?: return true
                //val uri = Uri.parse("geo:${attraction.location.latitude},${attraction.location.longitude}?z=9&q=${attraction.title}")
                //val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                //mapIntent.setPackage("com.google.android.apps.maps")
                //startActivity(mapIntent)
                //true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}