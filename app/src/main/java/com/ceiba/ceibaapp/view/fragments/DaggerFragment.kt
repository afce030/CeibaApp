package com.ceiba.ceibaapp.view.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class DaggerFragment: Fragment(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        injectPars()
        super.onAttach(context)
    }

    protected open fun injectPars() = AndroidSupportInjection.inject(this)

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

}