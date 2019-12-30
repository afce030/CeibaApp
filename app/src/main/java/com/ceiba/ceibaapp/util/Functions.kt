package com.ceiba.ceibaapp.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object Functions {

    fun <T : ViewModel> createVM(fragment: Fragment, viewModelFactory: ViewModelProvider.Factory, clazz: Class<T>): T {
        return ViewModelProvider(fragment, viewModelFactory)[clazz]
    }

}