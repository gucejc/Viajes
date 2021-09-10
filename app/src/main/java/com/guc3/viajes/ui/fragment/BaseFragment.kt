package com.guc3.viajes.ui.fragment

import androidx.fragment.app.Fragment
import com.guc3.viajes.ui.MainActivity

abstract class BaseFragment:Fragment() {
    protected  val navController by lazy {
        (activity as MainActivity).navController
    }
}