package com.guc3.viajes.ui.fragment

import androidx.fragment.app.Fragment
import com.guc3.viajes.arch.AttractionsViewModel
import com.guc3.viajes.data.Attraction
import com.guc3.viajes.ui.MainActivity

abstract class BaseFragment:Fragment() {
    protected  val navController by lazy {
        (activity as MainActivity).navController
    }

    protected  val activityViewModel:AttractionsViewModel
    get() = (activity as MainActivity).viewModel
}