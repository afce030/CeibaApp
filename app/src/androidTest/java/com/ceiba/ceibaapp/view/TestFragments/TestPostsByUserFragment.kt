package com.ceiba.ceibaapp.view.TestFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ceiba.ceibaapp.R
import com.ceiba.ceibaapp.model.repositories.MainRepository
import com.ceiba.ceibaapp.util.Functions
import com.ceiba.ceibaapp.view.fragments.PostsByUserFragment
import com.ceiba.ceibaapp.viewmodels.MainViewModel
import org.jetbrains.annotations.NotNull
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TestPostsByUserFragment: PostsByUserFragment() {

    private lateinit var viewmodel: MainViewModel

    private lateinit var mainRepo: MainRepository
    private lateinit var vm: MainViewModel

    @InjectMocks
    private lateinit var factory: ViewModelProvider.NewInstanceFactory

    override fun injectPars() {

        MockitoAnnotations.initMocks(this)

        mainRepo = Mockito.mock(MainRepository::class.java)
        viewmodel = MainViewModel(mainRepo)

        viewModelFactory = factory

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Mockito.`when`(ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]).
            thenReturn(MainViewModel(mainRepo))

        return super.onCreateView(inflater, container, savedInstanceState)
    }

}